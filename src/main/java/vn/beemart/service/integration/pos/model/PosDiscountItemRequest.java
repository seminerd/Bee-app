package vn.beemart.service.integration.pos.model;

import java.math.BigDecimal;

public class PosDiscountItemRequest {
    private int id;
    private BigDecimal rate;
    private String reason;
    private String source;
    private BigDecimal value;
}
