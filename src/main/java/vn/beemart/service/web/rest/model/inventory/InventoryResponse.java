package vn.beemart.service.web.rest.model.inventory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponse {

    private Integer locationId;
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
