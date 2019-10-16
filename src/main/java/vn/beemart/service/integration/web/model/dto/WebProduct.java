package vn.beemart.service.integration.web.model.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "web_products")
@ToString
public class WebProduct implements Serializable {
    private final static long serialVersionUID = -7813938566913635960L;
    @Id
    public Integer id;
    public String name;
    public String alias;
    public String vendor;
    public String productType;
    public String  metaTitle;
    public String metaDescription;
    public String summary;
    public Instant publishedOn;
    public String templateLayout;
    public Instant createdOn;
    public Instant modifiedOn;
    public String content;
    public String tags;
    @Transient
    public List<WebImage> images ;
    @Transient
    public WebImage image;
    @Transient
    public List<WebVariant> variants ;
    @Transient
    public List<WebOption> options ;
}
