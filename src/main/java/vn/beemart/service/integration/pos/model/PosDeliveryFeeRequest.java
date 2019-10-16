package vn.beemart.service.integration.pos.model;

import java.math.BigDecimal;

public class PosDeliveryFeeRequest {
    private BigDecimal fee;
    private int shippingCostId;
    private String shippingCostName;
}
