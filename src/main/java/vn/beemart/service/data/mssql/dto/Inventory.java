package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="bee_inventories")
@IdClass(InventoryId.class)
public class Inventory extends SqlDto implements Serializable {

    @Id
    private Integer locationId;
    @Id
    private Integer variantId;
    private Integer mac;
    @NotNull
    private Integer amount;
    private Integer onHand;
    private Integer available;
    private Integer committed;
    private Integer incoming;
    private Integer onway;
    private Integer minValue;
    private Integer maxValue;
    @NotNull
    private String binLocation;
    private Integer waitToPack;


}
