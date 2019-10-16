package vn.beemart.service.web.rest.model.cartitem;

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
@JsonRootName("item")
public class ChangeItemQuantityRequest {
    @NotNull
    @Min(0)
    @ApiModelProperty(required = true)
    private Integer quantity;
}

