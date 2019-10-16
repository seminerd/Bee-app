package vn.beemart.service.web.rest.model.cart;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.web.rest.model.cartitem.CartItemRequest;

@Getter
@Setter
@JsonRootName("cart")
public class NewCartRequest {
    @ApiModelProperty(required = true)
    private CartItemRequest cartItem;
}
