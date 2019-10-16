package vn.beemart.service.web.rest.model.order;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.data.mssql.dto.enumeration.OrderStatus;
import vn.beemart.service.web.rest.model.cart.CartResponse;
import vn.beemart.service.web.rest.model.checkout.CheckoutResponse;
import vn.beemart.service.web.rest.model.location.LocationResponse;
import vn.beemart.service.web.rest.model.payment.PaymentMethodResponse;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@JsonRootName("order")
public class OrderResponse {
    private int orderId;
    private String orderCode;
    private CartResponse cart;
    private CheckoutResponse checkout;
    private int accountId;
    private LocationResponse location;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private BigDecimal orderDiscount;
    private BigDecimal deliveryFee;
    private PaymentMethodResponse expectedPaymentMethod;
    private String note;
    private String os;
    private String device;
    private Instant createdOn;
    private Instant modifiedOn;
    private Instant shippedOn;
    private Instant goodsIssueOn;
}
