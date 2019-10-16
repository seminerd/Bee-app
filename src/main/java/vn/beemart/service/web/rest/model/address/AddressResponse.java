package vn.beemart.service.web.rest.model.address;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value="address")
public class AddressResponse {
    private int addressId;
    private int accountId;
    private String city;
    private String district;
    private String ward;
    private String address;
    private String phoneNumber;
    private int cityId;
    private int districtId;
    private int wardId;
    private boolean isDefault;
}
