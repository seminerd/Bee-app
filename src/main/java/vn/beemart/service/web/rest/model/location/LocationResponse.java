package vn.beemart.service.web.rest.model.location;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.data.mssql.dto.enumeration.LocationType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@JsonRootName("location")
public class LocationResponse {
    private Integer locationId;
    private String code;
    private String address;
    private String country;
    private String city;
    private String district;
    private Integer countryId;
    private Integer cityId;
    private Integer districtId;
    private String name;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private LocationType status;
}
