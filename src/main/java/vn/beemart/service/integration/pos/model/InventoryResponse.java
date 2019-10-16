package vn.beemart.service.integration.pos.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InventoryResponse {
    private int locationId;
    private BigDecimal mac;
    private BigDecimal amount;
    private BigDecimal onHand;
    private BigDecimal available;
    private BigDecimal committed;
    private BigDecimal incoming;
    private BigDecimal onway;
    private BigDecimal minValue;
    private BigDecimal maxValue;
}
