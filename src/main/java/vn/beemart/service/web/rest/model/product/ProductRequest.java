package vn.beemart.service.web.rest.model.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.web.rest.model.image.ImageRequest;
import vn.beemart.service.web.rest.model.image.ImageResponse;
import vn.beemart.service.web.rest.model.option.OptionRequest;
import vn.beemart.service.web.rest.model.option.OptionResponse;
import vn.beemart.service.web.rest.model.variant.VariantRequest;
import vn.beemart.service.web.rest.model.variant.VariantResponse;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductRequest {
    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(required = true)
    private String name;
    private int categoryId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Instant createdOn;
    private Instant modifiedOn;


    private String alias;
    private String vendor;
    private String brand;
    private Integer brandId;
    private String productType;
    private String summary;
    private Instant publishedOn;

    private String content;
    private String[] tags;
    private List<ImageRequest> images;
    private ImageResponse image;
    private List<VariantRequest> variants;
    private List<OptionRequest> options;
    private String status;
    private String description;
    private String category;

    private String categoryCode;


}
