package vn.beemart.service.web.rest.model.payment;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value="payment_methods")
public class ListPaymentMethodsResponse extends ArrayList<PaymentMethodResponse> {

    private static final long serialVersionUID = 2327484431573768721L;

    public ListPaymentMethodsResponse(List<PaymentMethodResponse> list) {
        super(list);
    }
}
