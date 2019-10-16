package vn.beemart.service.web.rest.model.address;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value="places")
public class ListPlacesResponse extends ArrayList<PlaceResponse> {

    private static final long serialVersionUID = -407378817229206165L;

    public ListPlacesResponse(List<PlaceResponse> placesResponse) {
        super(placesResponse);
    }
}
