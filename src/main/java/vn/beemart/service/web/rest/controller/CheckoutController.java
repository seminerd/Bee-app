package vn.beemart.service.web.rest.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.common.controller.BaseController;
import vn.beemart.service.common.validate.NotFoundException;
import vn.beemart.service.common.validate.ProhibitiveException;
import vn.beemart.service.data.mssql.dao.AddressDao;
import vn.beemart.service.data.mssql.dao.CartDao;
import vn.beemart.service.data.mssql.dao.CheckoutDao;
import vn.beemart.service.data.mssql.dao.PaymentMethodDao;
import vn.beemart.service.data.mssql.dto.Checkout;
import vn.beemart.service.data.mssql.dto.enumeration.CartStatus;
import vn.beemart.service.data.mssql.dto.enumeration.CheckoutStatus;
import vn.beemart.service.data.mssql.service.CheckoutService;
import vn.beemart.service.web.rest.model.checkout.CheckoutRequest;
import vn.beemart.service.web.rest.model.checkout.CheckoutResponse;
import vn.beemart.service.web.rest.model.checkout.CreateCheckoutRequest;

import javax.validation.Valid;
import java.util.Date;

@RestController
@CommonsLog(topic = "services")
@RequestMapping("/checkout")
public class CheckoutController extends BaseController {
    private final CheckoutDao checkoutDao;

    private final CartDao cartDao;

    private final PaymentMethodDao paymentMethodDao;

    private final AddressDao addressDao;

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutDao checkoutDao, CartDao cartDao, PaymentMethodDao paymentMethodDao, AddressDao addressDao, CheckoutService checkoutService) {
        this.checkoutDao = checkoutDao;
        this.cartDao = cartDao;
        this.paymentMethodDao = paymentMethodDao;
        this.addressDao = addressDao;
        this.checkoutService = checkoutService;
    }

    @ApiOperation(value = "Tạo checkout", notes = "Wrap json body bên dưới với json root {\"checkout\": {model_below}}")
    @PostMapping
    public CheckoutResponse createCheckout(@RequestBody @Valid CreateCheckoutRequest model) {
        if(!cartDao.checkValidCart(model.getCartId()))
            throw new NotFoundException("Giỏ hàng không tồn tại");
        val checkout = new Checkout();
        checkout.setStatus(CheckoutStatus.open);
        checkout.setCartId(model.getCartId());
        checkout.setCreatedOn(new Date().toInstant());
        setCheckoutInfo(model.getPaymentMethodId(), model.getBillingAddressId(), model.getShippingAddressId(), checkout);
        checkoutDao.store(checkout);
        val cart = cartDao.getById(model.getCartId());
        cart.setStatus(CartStatus.closed);
        cartDao.store(cart);
        try {
            return checkoutService.getCheckoutResponse(checkout.getCheckoutId());
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Lấy thông tin checkout dựa vào checkout id")
    @GetMapping("/{id}")
    public CheckoutResponse getById(@PathVariable(value = "id") int id) {
        try {
            return checkoutService.getCheckoutResponse(id);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Cập nhật thông tin checkout dựa vào checkout id", notes = "Wrap json body bên dưới với json root {\"checkout\": {model_below}}")
    @PostMapping("/{id}")
    public CheckoutResponse update(@PathVariable(value = "id") int id,@RequestBody @Valid CheckoutRequest model) {
        val checkout = checkoutDao.getById(id);
        if(checkout == null)
            throw new NotFoundException("Checkout "+id+" không tồn tại");
        if(checkout.getStatus().equals(CheckoutStatus.closed))
            throw new ProhibitiveException("Checkout "+id+" đã được tạo đơn hàng, không thể sửa thông tin");
        setCheckoutInfo(model.getPaymentMethodId(), model.getBillingAddressId(), model.getShippingAddressId(), checkout);
        checkoutDao.store(checkout);
        try {
            return checkoutService.getCheckoutResponse(id);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    private void setCheckoutInfo(Integer paymentMethodId, Integer billingAddressId, Integer shippingAddressId, Checkout checkout) {
        if(paymentMethodId != null) {
            if(paymentMethodDao.checkValidPaymentMethod(paymentMethodId))
                checkout.setPaymentMethodId(paymentMethodId);
            else
                throw new NotFoundException("Phương thức thanh toán không tồn tại");
        }
        if(billingAddressId != null) {
            if(addressDao.checkValidAddress(billingAddressId))
                checkout.setBillingAddressId(billingAddressId);
            else
                throw new NotFoundException("Địa chỉ nhận hóa đơn không tồn tại");
        }
        if(shippingAddressId != null) {
            if(addressDao.checkValidAddress(shippingAddressId))
                checkout.setShippingAddressId(shippingAddressId);
            else
                throw new NotFoundException("Địa chỉ giao hàng không tồn tại");
        }
    }
}
