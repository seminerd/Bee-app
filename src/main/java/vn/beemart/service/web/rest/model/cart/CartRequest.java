package vn.beemart.service.web.rest.model.cart;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonRootName("cart")
public class CartRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private Integer cartId;
    @NotNull
    @ApiModelProperty(required = true)
    private Integer accountId;
    @Min(0)
    private Integer point;
}
