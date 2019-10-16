package vn.beemart.service.web.rest.model.location;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import vn.beemart.service.data.mssql.dto.enumeration.LocationType;

import javax.persistence.*;

@Getter
@Setter
@JsonRootName("location")
public class CreateLocationRequest {
    @Length(max = 250)
    private String address;
    private Integer countryId;
    private Integer cityId;
    private Integer districtId;
    private String name;
    private String phoneNumber;
}
