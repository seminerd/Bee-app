package vn.beemart.service.web.rest.model.option;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.time.Instant;

public class OptionRequest {
    @Size(max = 255)
    @ApiModelProperty(required = true)
    public String name;
    public Integer position;
    public String createdOn;
    public Instant modifiedOn;
    public String[] values;
    private Integer productId;

}
