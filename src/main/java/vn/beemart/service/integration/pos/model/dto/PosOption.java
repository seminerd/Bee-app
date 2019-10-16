package vn.beemart.service.integration.pos.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="pos_options")
public class PosOption implements Serializable {
    @Id
    public Integer id;
    public String name;
    public Integer position;
    private Integer productId;



}

