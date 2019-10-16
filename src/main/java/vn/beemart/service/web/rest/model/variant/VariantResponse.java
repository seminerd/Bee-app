package vn.beemart.service.web.rest.model.variant;

import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.web.rest.model.inventory.InventoryResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VariantResponse {
    private Integer id;
    private String name;
    private String barcode;
    private String sku;
    private BigDecimal price;
    private BigDecimal compareAtPrice;
    private String option1;
    private String option2;
    private String option3;
    private boolean taxable;
    private List<InventoryResponse> inventories= new ArrayList<>();
    private Integer quantity; // ->> from sum of inventories
    private Double weight;
    private String weightUnit;
    private Double grams;
    private Integer imageId;
    private Integer position;
    private Instant createdOn;
    private Instant modifiedOn;
    private String title;
    private Integer productId;
    public void addInventory(InventoryResponse inventoryResponse){
        this.inventories.add(inventoryResponse);
    }

}
