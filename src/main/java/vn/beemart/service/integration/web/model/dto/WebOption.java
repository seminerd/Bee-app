package vn.beemart.service.integration.web.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "web_options")
public class WebOption implements Serializable {

    private final static long serialVersionUID = -3429865065105340941L;
    @Id
    public Integer id;
    public String name;
    public Integer position;
    public Instant createdOn;
    public Instant modifiedOn;


}
