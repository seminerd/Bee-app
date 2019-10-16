package vn.beemart.service.web.rest.model.address;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value="addresses")
public class ListAddressesResponse extends ArrayList<AddressResponse> {

    private static final long serialVersionUID = 5016418168081097957L;

    public ListAddressesResponse(List<AddressResponse> addresses) {
        super(addresses);
    }
}
