package vn.beemart.service.integration.pos.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "pos_variant_prices")
public class PosVariantPrice implements Serializable {

    @Id
    private Integer id;
    private Double value;
    private String name;
    private Integer priceListId;
    private Integer productId;
    private Integer variantId;
}
