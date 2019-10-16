package vn.beemart.service.integration.pos.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class VariantResponse {
    private Instant createdOn;
    private Instant modifiedOn;
    private String name;
    private String sku;
    private String barcode;
    private List<InventoryResponse> inventories;
}
