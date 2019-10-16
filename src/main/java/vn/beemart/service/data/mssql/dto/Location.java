package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import vn.beemart.service.data.mssql.dto.enumeration.LocationStatus;
import vn.beemart.service.data.mssql.dto.enumeration.LocationType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Entity
@Setter
@Table(name = "locations")
public class Location extends SqlDto implements Serializable {

    private static final long serialVersionUID = 9093553695899768218L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer locationId;
    private String code;
    @Length(max = 250)
    private String address;
    @Length(max = 50)
    private String country;
    @Length(max = 50)
    private String city;
    @Length(max = 50)
    private String district;
    private Integer countryId;
    private Integer cityId;
    private Integer districtId;
    private String name;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private LocationType status;
}
