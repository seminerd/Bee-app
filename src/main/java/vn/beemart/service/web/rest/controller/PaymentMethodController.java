package vn.beemart.service.web.rest.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.beemart.service.common.validate.NotFoundException;
import vn.beemart.service.data.mssql.service.PaymentMethodService;
import vn.beemart.service.web.rest.model.payment.ListPaymentMethodsResponse;

@RestController
@CommonsLog(topic = "services")
@RequestMapping("/payment_methods")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @ApiOperation("Lấy thông tin tất cả phương thức thanh toán")
    @GetMapping
    public ListPaymentMethodsResponse getAllMethods() {
        try {
            return paymentMethodService.getAllPaymentMethodsResponse();
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
