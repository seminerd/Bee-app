package vn.beemart.service.integration.pos.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="pos_images")
public class PosImage implements Serializable {
    @Id
    private Integer id;
    private Double size;
    private Instant createdOn;
    private Instant modifiedOn;
    private String path;
    private String fullPath;
    private String fileName;
    private Boolean isDefault;
    private Integer position;
    private Integer productId;


    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
