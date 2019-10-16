package vn.beemart.service.web.rest.model.option;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OptionResponse {
    public Integer id;

    public String name;
    public Integer position;
    public Instant createdOn;
    public Instant modifiedOn;
    public String[] values;
}
