package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name="bee_products")
@Document(indexName = "products",type = "products")
public class Product extends SqlDto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    @Max(255)
    private String name;
    @NotBlank
    private String alias;
    private String vendor;
    private String brand;
    private Integer brandId;
    @NotBlank
    private String productType;
    private String summary;
    @NotNull
    private Instant publishedOn;
    @NotNull
    private Instant createdOn;
    private Instant modifiedOn;
    private String content;
    private String[] tags;
    private String status;
    private String description;
    private String category;
    private Integer categoryId;
    private String categoryCode;


}
