package vn.beemart.service.integration.web.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="web_variants")
public class WebVariant implements Serializable {

    private final static long serialVersionUID = 2539012330983120039L;
    @Id
    private Integer id;
    private String barcode;
    private String sku;
    private Double price;
    private BigDecimal compareAtPrice;
    private String option1;
    private String option2;
    private String option3;
    private Boolean taxable;
    private String inventoryManagement;
    private String inventoryPolicy;
    private Integer inventoryQuantity;
    private Boolean requiresShipping;
    private Double weight;
    private String weightUnit;
    private Integer imageId;
    private Integer position;
    private Instant createdOn;
    private Instant modifiedOn;
    private String title;
    private Integer grams;
    private Integer productId;
}
