package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name="bee_images")
@Getter
@Setter
public class Image extends SqlDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer position;
    @NotNull
    private String source;
    private String alt;
    @NotNull
    private Instant createdOn;
    @NotNull
    private Instant modifiedOn;
    @NotNull
    private String filename;
}
