package vn.beemart.service.integration.pos.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pos_variants")
public class PosVariant implements Serializable {
    @Id

    private Integer id;
    private Integer tenantId;
    private Integer locationId;
    private Instant createdOn;
    private Instant modifiedOn;
    private Integer categoryId;
    private Integer brandId;
    private Integer productId;
    private Boolean composite;
    private Double initPrice;
    private Double initStock;
    private Double variantRetailPrice;
    private Double variantWholePrice;
    private Double variantImportPrice;
    private Integer imageId;
    private String description;
    private String name;
    private String opt1;
    private String opt2;
    private String opt3;
    private String productName;
    private String productStatus;
    private String status;
    private Boolean sellable;
    private String sku;
    private String barcode;
    private Boolean taxable;
    private Double weightValue;
    private String weightUnit;
    private String unit;
    private Boolean packsize;
    private Integer packsizeQuantity;
    private Integer packsizeRootId;
    private String productType;
    @Transient
    private List<PosVariantPrice> variantPrices;
    @Transient
    private List<PosInventory> inventories;
    @Transient
    private List<PosImage> images;
    @Transient
    private List<PosCompositeItem> compositeItems;
}
