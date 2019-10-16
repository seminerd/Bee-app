package vn.beemart.service.integration.pos.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
@JsonRootName("data_error")
public class PosErrorMessage {
    private int status;
    private JSONObject errors;
}
