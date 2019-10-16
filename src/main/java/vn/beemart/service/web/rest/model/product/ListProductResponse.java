package vn.beemart.service.web.rest.model.product;

import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.integration.pos.model.BaseListResponse;
import java.util.List;
@Getter
@Setter
public class ListProductResponse extends BaseListResponse {
    private List<ProductResponse> products;

}
