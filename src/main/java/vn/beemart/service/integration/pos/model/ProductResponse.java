package vn.beemart.service.integration.pos.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private String name;
    private Instant createdOn;
    private Instant modifiedOn;
    private String status;
    private String productType;
    private List<VariantResponse> variants;
}
