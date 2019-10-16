package vn.beemart.service.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class KafkaMessageModel {
    private HashMap<String, Object> headers;
    private String payload;
}
