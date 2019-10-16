package vn.beemart.service.data.mssql.service;

import lombok.val;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.CheckoutDao;
import vn.beemart.service.web.rest.model.checkout.CheckoutResponse;

@Service
public class CheckoutService {
    private final MapperFacade mapperFacade;

    private final CheckoutDao checkoutDao;

    private final AddressService addressService;

    private final CartService cartService;

    private final PaymentMethodService paymentMethodService;

    public CheckoutService(MapperFacade mapperFacade, CheckoutDao checkoutDao, AddressService addressService, CartService cartService, PaymentMethodService paymentMethodService) {
        this.mapperFacade = mapperFacade;
        this.checkoutDao = checkoutDao;
        this.addressService = addressService;
        this.cartService = cartService;
        this.paymentMethodService = paymentMethodService;
    }

    public CheckoutResponse getCheckoutResponse(Integer checkoutId) throws Exception {
        if(checkoutId == null || !checkoutDao.checkValidCheckout(checkoutId)) {
            throw new Exception("Checkout "+checkoutId+" không tồn tại");
        }
        val checkout = checkoutDao.getById(checkoutId);
        val response = mapperFacade.map(checkout, CheckoutResponse.class);
        response.setCart(cartService.getCartResponse(checkout.getCartId()));
        if(checkout.getBillingAddressId() != null) {
            int billingAddressId = checkout.getBillingAddressId();
            response.setBillingAddress(addressService.getAddressResponse(billingAddressId));
        }
        if(checkout.getShippingAddressId() != null) {
            int shippingAddressId = checkout.getShippingAddressId();
            response.setShippingAddress(addressService.getAddressResponse(shippingAddressId));
        }
        if(checkout.getPaymentMethodId() != null) {
            int paymentMethodId = checkout.getPaymentMethodId();
            response.setPaymentMethod(paymentMethodService.getPaymentMethodResponse(paymentMethodId));
        }
        return response;
    }
}
