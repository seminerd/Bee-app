package vn.beemart.service.web.rest.model.product;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.beemart.service.web.rest.model.image.ImageResponse;
import vn.beemart.service.web.rest.model.option.OptionResponse;
import vn.beemart.service.web.rest.model.variant.VariantResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@JsonRootName(value = "product")
@NoArgsConstructor
public class ProductResponse {

    private Integer id;
    private String name;
    private String alias;
    private String vendor;
    private String brand;
    private Integer brandId;
    private String productType;
    private String summary;
    private Instant publishedOn;
    private Instant createdOn;
    private Instant modifiedOn;
    private String content;
    private String[] tags;
    private List<ImageResponse> images = new ArrayList<>();
    private ImageResponse image;
    private List<VariantResponse> variants = new ArrayList<>();
    private List<OptionResponse> options = new ArrayList<>();
    private String status;
    private String description;
    private String category;
    private Integer categoryId;
    private String categoryCode;
    public void addVariant(VariantResponse variantResponse){
        this.variants.add(variantResponse);
    }
    public void addOption(OptionResponse optionResponse){
        this.options.add(optionResponse);
    }
    public void addImage(ImageResponse image){
        this.images.add(image);
    }




}
