package vn.beemart.service.web.rest.model.image;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ImageResponse {
    private Integer id;
    private Integer productId;
    private Integer position;
    private String source;
    private String alt;
    private Instant createdOn;
    private Instant modifiedOn;
    private String filename;
}
