package vn.beemart.service.integration.pos.model.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@JsonRootName("product")
@Table(name="pos_products")
public class PosProduct {

    @Id
    public Integer id;
    public Integer tenantId;
    public Instant createdOn;
    public Instant modifiedOn;
    public String status;
    public Integer brandId;
    public String brand;
    public String description;
    public String imagePath;
    public String imageName;
    public String name;
    public String opt1;
    public String opt2;
    public String opt3;
    public Integer categoryId;
    public String category;
    public String categoryCode;
    public String tags;
    public String productType;
    @Transient
    public List<PosVariant> variants ;
    @Transient
    public List<PosOption> options ;
    @Transient
    public List<PosImage> images ;


}

