package vn.beemart.service.integration.pos.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="pos_inventories")
@IdClass(PosInventoryId.class)
public class PosInventory implements Serializable {

    @Id
    private Integer locationId;
    @Id
    private Integer variantId;
    private Integer mac;
    private Integer amount;
    private Integer onHand;
    private Integer available;
    private Integer committed;
    private Integer incoming;
    private Integer onway;
    private Integer minValue;
    private Integer maxValue;
    private String binLocation;
    private Integer waitToPack;


}
