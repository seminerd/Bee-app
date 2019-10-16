package vn.beemart.service.integration.pos.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListResponse extends BaseListResponse {

    private List<ProductResponse> products;

}
