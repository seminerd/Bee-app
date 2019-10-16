package vn.beemart.service.integration.pos.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PosOrderLineItemRequest {
    private int id;
    private BigDecimal price;
    private int productId;
    private String productName;
    private int quantity;
    private int variantId;
    private String variantName;
}
