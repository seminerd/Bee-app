package vn.beemart.service.web.rest.model.image;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ImageRequest {
    @NotNull
    private Integer productId;
    @NotNull
    private Integer position;
    @NotNull
    private String source;
    private String alt;
    private String filename;
}
