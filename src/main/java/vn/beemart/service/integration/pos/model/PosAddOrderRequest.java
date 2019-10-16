package vn.beemart.service.integration.pos.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
public class PosAddOrderRequest extends BaseIgnoreRootJsonModel {
    private PosAddressRequest billingAddress;
    private String code;
    private Instant createdOn;
    private int customerId;
    private PosDeliveryFeeRequest deliveryFee;
    private List<PosDiscountItemRequest> discountItems;
    private String email;
    private int expectedDeliveryProviderId;
    private String expectedDeliveryType;
    private int expectedPaymentMethodId;
    private Instant issueOn;
    private int locationId;
    private String note;
    private List<PosOrderLineItemRequest> orderLineItems;
    private String phoneNumber;
    private List<PosPrepaymentRequest> prepayments;
    private String promotionCode;
    private String promotionName;
    private int promotionRedemptionId;
    private Instant shipOn;
    private Instant shipOnMax;
    private Instant shipOnMin;
    private PosAddressRequest shippingAddress;
    private int sourceId;
    private String sourceUrl;
    private String status;
    private String taxTreatment;
}
