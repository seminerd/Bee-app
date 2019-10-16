package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Entity
@Setter
@Table(name = "addresses")
public class Address extends SqlDto implements Serializable {

    private static final long serialVersionUID = 1750389127250874469L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer addressId;
    @NotNull
    private Integer accountId;
    @NotNull
    @Length(max = 50)
    private String city;
    @NotNull
    @Length(max = 50)
    private String district;
    @NotNull
    @Length(max = 50)
    private String ward;
    @NotNull
    @Length(max = 250)
    private String address;
    @NotNull
    @Length(max = 15)
    private String phoneNumber;
    @NotNull
    private Integer cityId;
    @NotNull
    private Integer districtId;
    @NotNull
    private Integer wardId;
    @NotNull
    private boolean isDefault;
}
