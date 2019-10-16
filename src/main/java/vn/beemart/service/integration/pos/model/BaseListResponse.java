package vn.beemart.service.integration.pos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseListResponse extends BaseIgnoreRootJsonModel {
    private Metadata metadata;
}
