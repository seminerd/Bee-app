package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="bee_options")
public class Option extends SqlDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @NotNull
    public String name;
    @NotNull
    public Integer position;
    @NotNull
    public Instant createdOn;
    public Instant modifiedOn;
    @NotNull
    @Column(name="_values")
    public String[] values;
    @NotNull
    private Integer productId;
}
