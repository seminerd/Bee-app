package vn.beemart.service.data.mssql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryId implements Serializable {

    private Integer locationId;

    private Integer variantId;
}
