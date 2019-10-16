package vn.beemart.service.web.rest.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.common.controller.BaseController;
import vn.beemart.service.common.validate.NotFoundException;
import vn.beemart.service.data.mssql.dao.*;
import vn.beemart.service.data.mssql.dto.Order;
import vn.beemart.service.data.mssql.dto.OrderCode;
import vn.beemart.service.data.mssql.dto.enumeration.CheckoutStatus;
import vn.beemart.service.data.mssql.dto.enumeration.OrderStatus;
import vn.beemart.service.data.mssql.repository.OrderCodeRepository;
import vn.beemart.service.data.mssql.service.CartService;
import vn.beemart.service.data.mssql.service.CheckoutService;
import vn.beemart.service.data.mssql.service.PaymentMethodService;
import vn.beemart.service.integration.pos.client.OrderClient;
import vn.beemart.service.web.rest.model.order.ListOrdersResponse;
import vn.beemart.service.web.rest.model.order.NewOrderRequest;
import vn.beemart.service.web.rest.model.order.OrderResponse;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CommonsLog(topic = "services")
@RequestMapping("/orders")
public class OrderController extends BaseController {

    private final OrderDao orderDao;

    private final CartDao cartDao;

    private final CartItemDao cartItemDao;

    private final CheckoutDao checkoutDao;

    private final PaymentMethodDao paymentMethodDao;

    private final AddressDao addressDao;

    private final CartService cartService;

    private final CheckoutService checkoutService;

    private final PaymentMethodService paymentMethodService;

    private final OrderClient orderClient;

    private final OrderCodeRepository orderCodeRepository;

    public OrderController(OrderDao orderDao, CartDao cartDao, CartItemDao cartItemDao, CheckoutDao checkoutDao, PaymentMethodDao paymentMethodDao, AddressDao addressDao, CartService cartService, CheckoutService checkoutService, PaymentMethodService paymentMethodService, OrderClient orderClient, OrderCodeRepository orderCodeRepository) {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
        this.checkoutDao = checkoutDao;
        this.paymentMethodDao = paymentMethodDao;
        this.addressDao = addressDao;
        this.cartService = cartService;
        this.checkoutService = checkoutService;
        this.paymentMethodService = paymentMethodService;
        this.orderClient = orderClient;
        this.orderCodeRepository = orderCodeRepository;
    }

    @ApiOperation(value = "Lấy thông tin tất cả các đơn hàng của một Account bằng Account Id")
    @GetMapping
    public ListOrdersResponse getAllOrders(@RequestHeader("X-Sapo-AccountId") int accountId) {
        val orders = orderDao.getOrdersByAccountId(accountId);
        List<OrderResponse> listResponses = orders.stream().map(order -> {
            try {
                val orderResponse = mapperFacade.map(order, OrderResponse.class);
                orderResponse.setCart(cartService.getCartResponse(order.getCartId()));
                orderResponse.setCheckout(checkoutService.getCheckoutResponse(order.getCheckoutId()));
                val checkout = checkoutDao.getById(order.getCheckoutId());
                orderResponse.setExpectedPaymentMethod(paymentMethodService.getPaymentMethodResponse(checkout.getPaymentMethodId()));
                return orderResponse;
            } catch (Exception e) {
                throw new NotFoundException(e.getMessage());
            }
        }).collect(Collectors.toList());
        return new ListOrdersResponse(listResponses);
    }

    @ApiOperation(value = "Tạo đơn hàng", notes = "Wrap json body bên dưới với json root {\"order\": {model_below}}")
    @PostMapping
    public OrderResponse createOrder(@RequestBody @Valid NewOrderRequest model) {
        val newOrder = new Order();
        newOrder.setStatus(OrderStatus.draft);
        newOrder.setGoodsIssueOn(new Date().toInstant());
        val checkout = checkoutDao.getById(model.getCheckoutId());
        if(checkout == null) {
            throw new NotFoundException("Checkout "+model.getCheckoutId()+" không tồn tại!");
        }
        newOrder.setCheckoutId(model.getCheckoutId());
        val cart = cartDao.getById(checkout.getCartId());
        if(cart == null) {
            throw new NotFoundException("Cart "+checkout.getCartId()+" không tồn tại!");
        }
        newOrder.setCartId(checkout.getCartId());
        newOrder.setAccountId(cart.getAccountId());
        newOrder.setExpectedPaymentMethodId(checkout.getPaymentMethodId());
        newOrder.setDevice(model.getDevice());
        newOrder.setOs(model.getOs());
        val newOrderCode = new OrderCode();
        orderCodeRepository.save(newOrderCode);
        newOrder.setOrderCode(newOrderCode.toString());
        newOrder.setHasSyncWithPos(false);
        newOrder.setCreatedOn(new Date().toInstant());
        val order = orderDao.store(newOrder);
        checkout.setStatus(CheckoutStatus.closed);
        checkoutDao.store(checkout);
        try {
            val response = mapperFacade.map(order, OrderResponse.class);
            response.setCart(cartService.getCartResponse(checkout.getCartId()));
            response.setCheckout(checkoutService.getCheckoutResponse(model.getCheckoutId()));
            response.setExpectedPaymentMethod(paymentMethodService.getPaymentMethodResponse(checkout.getPaymentMethodId()));
            return response;
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Lấy thông tin đơn hàng bằng Id đơn hàng")
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable("id") int orderId) {
        val order = orderDao.getById(orderId);
        if(order == null) {
            throw new NotFoundException("Order "+orderId+" không tồn tại!");
        }
        try {
            val response = mapperFacade.map(order, OrderResponse.class);
            response.setCart(cartService.getCartResponse(order.getCartId()));
            response.setCheckout(checkoutService.getCheckoutResponse(order.getCheckoutId()));
            val checkout = checkoutDao.getById(order.getCheckoutId());
            response.setExpectedPaymentMethod(paymentMethodService.getPaymentMethodResponse(checkout.getPaymentMethodId()));
            return response;
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

}
