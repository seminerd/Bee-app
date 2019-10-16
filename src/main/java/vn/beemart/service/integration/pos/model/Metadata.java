package vn.beemart.service.integration.pos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Metadata {
    private int total;
    private int page;
    private int limit;

}
