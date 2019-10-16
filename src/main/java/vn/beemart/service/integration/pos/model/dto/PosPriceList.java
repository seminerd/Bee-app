package vn.beemart.service.integration.pos.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="pos_pricelists")
public class PosPriceList implements Serializable {

    @Id
    public Integer id;
    public Integer tenantId;
    public Instant createdOn;
    public Instant modifiedOn;
    public String code;
    public String name;
    public Boolean isCost;
    public Integer currencyId;
    public String status;
    public Boolean init;
}
