package vn.beemart.service.web.rest.model.order;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonRootName("order")
public class NewOrderRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private Integer checkoutId;
    @ApiModelProperty(required = true)
    @NotNull
    private Integer locationId;
    private String note;
    @ApiModelProperty(required = true)
    @NotNull
    private String os;
    @ApiModelProperty(required = true)
    @NotNull
    private String device;
}
