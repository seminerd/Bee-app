package vn.beemart.service.web.rest.model.checkout;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonRootName("checkout")
public class CreateCheckoutRequest {
    @NotNull
    private Integer cartId;
    private Integer billingAddressId;
    private Integer shippingAddressId;
    private Integer paymentMethodId;
}
