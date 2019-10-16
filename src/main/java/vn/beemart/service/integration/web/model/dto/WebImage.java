package vn.beemart.service.integration.web.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "web_images")
public class WebImage implements Serializable {

    private final static long serialVersionUID = -2811917565365562051L;
    public Integer size;
    @Id
    public Integer id;
    public Integer productId;
    public Integer position;
    public Integer[] variantIds;
    public Instant createdOn;
    public Instant modifiedOn;
    public String src;
    public String alt;
    public String filename;
    public Integer width;
    public Integer height;

}
