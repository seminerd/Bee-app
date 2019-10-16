package vn.beemart.service.web.rest.model.address;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value="place")
public class PlaceResponse {
    private int id;
    private int cityId;
    private int districtId;
    private int wardId;
    private String cityName;
    private String districtName;
    private String wardName;
}
