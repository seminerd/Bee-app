package vn.beemart.service.web.rest.model.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class InventoryRequest {
    @NotNull
    private Integer locationId;
    @NotNull
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
    @NotNull
    private String binLocation;
    private Integer waitToPack;
}
