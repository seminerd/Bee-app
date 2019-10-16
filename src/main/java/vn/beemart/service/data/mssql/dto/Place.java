package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "places")
@Getter
@Setter
public class Place extends SqlDto implements Serializable {

    private static final long serialVersionUID = -7295960883677107445L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private int cityId;
    @NotNull
    private int districtId;
    @NotNull
    private int wardId;
    @NotNull
    @Length(max = 50)
    private String cityName;
    @NotNull
    @Length(max = 50)
    private String districtName;
    @NotNull
    @Length(max = 50)
    private String wardName;
}
