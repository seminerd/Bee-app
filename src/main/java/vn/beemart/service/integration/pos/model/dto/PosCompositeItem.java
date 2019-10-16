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
@Table(name="pos_composite_items")
public class PosCompositeItem implements Serializable {
    @Id
    private Long subVariantId;
    private double price;
    private double quantity;
}

