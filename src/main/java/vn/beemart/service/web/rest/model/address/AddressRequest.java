package vn.beemart.service.web.rest.model.address;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonRootName("address")
public class AddressRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private int accountId;
    @ApiModelProperty(required = true)
    @NotNull
    @Length(max = 15)
    private String phoneNumber;
    @ApiModelProperty(required = true)
    @NotNull
    private int cityId;
    @ApiModelProperty(required = true)
    @NotNull
    private int districtId;
    @ApiModelProperty(required = true)
    @NotNull
    private int wardId;
    @ApiModelProperty(required = true)
    @NotNull
    @Length(max = 250)
    private String address;
    private boolean isDefault;
}
