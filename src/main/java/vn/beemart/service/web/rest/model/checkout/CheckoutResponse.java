package vn.beemart.service.web.rest.model.checkout;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.data.mssql.dto.enumeration.CheckoutStatus;
import vn.beemart.service.web.rest.model.address.AddressResponse;
import vn.beemart.service.web.rest.model.cart.CartResponse;
import vn.beemart.service.web.rest.model.payment.PaymentMethodResponse;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Getter
@Setter
@JsonRootName("checkout")
public class CheckoutResponse {
    private Integer checkoutId;
    private CartResponse cart;
    private AddressResponse billingAddress;
    private AddressResponse shippingAddress;
    private PaymentMethodResponse paymentMethod;
    @Enumerated(EnumType.STRING)
    private CheckoutStatus status;
    private Instant createdOn;
    private Instant completedOn;
    private Integer orderId;
}
