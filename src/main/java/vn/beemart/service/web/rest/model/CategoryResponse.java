package vn.beemart.service.web.rest.model;

import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.web.rest.model.product.ProductResponse;

import java.util.List;

@Getter
@Setter
public class CategoryResponse {
    private Integer id;
    private String name;
    private List<ProductResponse> products;
}
