package vn.beemart.service.integration.pos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PosInventoryId implements Serializable {

    private Integer locationId;

    private Integer variantId;
}
