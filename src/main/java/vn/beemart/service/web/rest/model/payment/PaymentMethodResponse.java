package vn.beemart.service.web.rest.model.payment;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("payment_method")
public class PaymentMethodResponse {
    private int paymentMethodId;
    private String name;
    private String type;
    private boolean integrated;
}
