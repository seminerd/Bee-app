package vn.beemart.service.web.rest.model.cartitem;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonRootName(value = "item")
public class CartItemResponse {
    private Integer cartItemId;
    private Integer productId;
    private Integer variantId;
    private Integer cartId;
    private String productName;
    private String variantName;
    private String productImage;
    private BigDecimal price;
    private BigDecimal comparePrice;
    private Integer quantity;
}
