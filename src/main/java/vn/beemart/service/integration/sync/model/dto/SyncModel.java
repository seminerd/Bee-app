package vn.beemart.service.integration.sync.model.dto;

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
@Table(name = "syncing_datas")
public class SyncModel implements Serializable {
    private final static long serialVersionUID = 5800538973701851205L;
    @Id
    public Integer id;
    public Integer bwProductId;
    public Integer bwVariantId;
    public Integer sapoProductId;
    public Integer sapoVariantId;
    public Integer storeId;
    public String bwSku;
    public String sapoSku;
    public String status;
    public Instant createdOn;
    public Instant modifiedOn;

}
