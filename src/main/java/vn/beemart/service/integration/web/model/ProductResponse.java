package vn.beemart.service.integration.web.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("products")
public class ProductResponse {
    private int id;
    private String name;
    private String alias;
}
