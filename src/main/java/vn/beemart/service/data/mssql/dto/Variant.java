package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "bee_variants")
public class Variant extends SqlDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Max(13)
    private String barcode;
    @NotNull
    @Max(13)
    private String sku;
    @NotNull
    private BigDecimal price;
    private BigDecimal compareAtPrice;
    private String option1;
    private String option2;
    private String option3;
    private boolean taxable;
    private Integer quantity; // ->> from sum of inventories
    private Double weight;
    private String weightUnit;
    private Double grams;
    private Integer imageId;
    private Integer position;
    @NotNull
    private Instant createdOn;
    @NotNull
    private Instant modifiedOn;
    @NotNull
    private String title;
    @NotNull
    private Integer productId;
    @NotNull
    @Max(250)
    private String name;
}
