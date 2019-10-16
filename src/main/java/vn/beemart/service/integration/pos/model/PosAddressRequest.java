package vn.beemart.service.integration.pos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PosAddressRequest {
    private String address1;
    private String address2;
    private String city;
    private String district;
    private String email;
    private String firstName;
    private String fullName;
    private String label;
    private String lastName;
    private String phoneNumber;
    private String ward;
    private String zipCode;
}
