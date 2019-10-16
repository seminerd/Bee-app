package vn.beemart.service.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import vn.beemart.service.common.annotation.ConditionalOnEnableOrderJob;
import vn.beemart.service.data.mssql.dao.*;
import vn.beemart.service.integration.pos.client.OrderClient;
import vn.beemart.service.integration.pos.model.PosAddOrderRequest;
import vn.beemart.service.integration.pos.model.PosAddressRequest;
import vn.beemart.service.integration.pos.model.PosErrorMessage;
import vn.beemart.service.integration.pos.model.PosOrderLineItemRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@ConditionalOnEnableOrderJob
@Component
@CommonsLog(topic = "job-push-order-to-pos")
public class PushOrderToPosService{

    @Value("${beemart.pos.base-url}")
    private String beemartBaseUrl;

    @Value("${beemart.pos.token}")
    private String token;

    private final OrderClient orderClient;

    private final OrderDao orderDao;

    private final CartItemDao cartItemDao;

    private final AddressDao addressDao;

    private final CheckoutDao checkoutDao;

    private final ObjectMapper mapper;

    private ThreadPoolTaskExecutor pool;

    public PushOrderToPosService(OrderClient orderClient, OrderDao orderDao, CartItemDao cartItemDao, AddressDao addressDao, CheckoutDao checkoutDao, @Qualifier("json_with_root") ObjectMapper mapper) {
        this.orderClient = orderClient;
        this.orderDao = orderDao;
        this.cartItemDao = cartItemDao;
        this.addressDao = addressDao;
        this.checkoutDao = checkoutDao;
        this.mapper = mapper;

        pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(10);
        pool.setMaxPoolSize(20);
        pool.setAllowCoreThreadTimeOut(true);
        pool.setThreadGroupName("push-order-job");
        pool.setThreadNamePrefix("push-order-job-");
        pool.initialize();
    }

    private List<PosOrderLineItemRequest> cartItemsToLineItems(int cartId) {
        val cartItems = cartItemDao.getItemsByCartId(cartId);
        return cartItems.stream().map(item -> {
            val lineItem = new PosOrderLineItemRequest();
            lineItem.setId(item.getCartItemId());
            lineItem.setProductId(item.getProductId());
            lineItem.setVariantId(item.getVariantId());
            lineItem.setQuantity(item.getQuantity());
            lineItem.setPrice(item.getPrice());
            return lineItem;
        }).collect(Collectors.toList());
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void scheduleTask() {
        val ordersToPush = orderDao.getOrdersHasNotSyncWithPos();
        ordersToPush.forEach(order -> {
            pool.execute(() -> {
                try {
                    val request = new PosAddOrderRequest();
                    val checkout = checkoutDao.getById(order.getCheckoutId());
                    request.setBillingAddress(addressToPosAddress(checkout.getBillingAddressId()));
                    request.setCode(order.getOrderCode());
                    request.setCreatedOn(order.getCreatedOn());
                    request.setNote(order.getNote());
                    request.setExpectedPaymentMethodId(order.getExpectedPaymentMethodId());
                    request.setLocationId(order.getLocationId());
                    request.setShippingAddress(addressToPosAddress(checkout.getShippingAddressId()));
                    request.setStatus(order.getStatus().toString());
                    request.setTaxTreatment("exclusive");
                    request.setOrderLineItems(cartItemsToLineItems(order.getCartId()));
                    request.setSourceId(1356535);
                    request.setSourceUrl("");
                    request.setCustomerId(order.getAccountId());
                    orderClient.addOrder(new URI(beemartBaseUrl), token, String.valueOf(order.getLocationId()), request);
                    order.setHasSyncWithPos(true);
                    orderDao.store(order);
                } catch (FeignException | URISyntaxException ex) {
                    log.error(ex);
                    if(ex instanceof FeignException) {
                        log.error(((FeignException) ex).contentUTF8());
                        try {
                            val errors = mapper.readValue(((FeignException) ex).contentUTF8(), PosErrorMessage.class);
                            if(errors.getErrors().containsKey("code")){
                                if(errors.getErrors().get("code").equals("Mã đơn hàng đã tồn tại")) {
                                    order.setHasSyncWithPos(true);
                                    orderDao.store(order);
                                }
                            }
                        } catch (IOException e) {
                            log.error(e);
                        }
                    }
                }
            });
        });
    }

    private PosAddressRequest addressToPosAddress(int addressId) {
        val address = addressDao.getById(addressId);
        val posAddress = new PosAddressRequest();
        posAddress.setAddress1(address.getAddress());
        posAddress.setCity(address.getCity());
        posAddress.setDistrict(address.getDistrict());
        posAddress.setPhoneNumber(address.getPhoneNumber());
        posAddress.setWard(address.getWard());
        return posAddress;
    }
}
