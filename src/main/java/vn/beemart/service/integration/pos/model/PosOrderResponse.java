package vn.beemart.service.integration.pos.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value = "order")
public class PosOrderResponse {
    private int id;
    private String code;
}
