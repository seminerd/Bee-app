package vn.beemart.service.web.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.common.model.KafkaMessageModel;

import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CommonsLog
@RestController
@RequestMapping("/listeners/web")
public class WebListenerController {
    private KafkaTemplate<String, String> kafkaTemplate;
    private String productTopic;
    private String productPartitionKeyHeader;
    private ObjectMapper noRootMapper;
    public WebListenerController(KafkaTemplate<String, String> kafkaTemplate,
                                 @Qualifier("json_without_root") ObjectMapper noRootMapper,
                                 @Value("${beemart.integration.web.kafka.product-topic}") String productTopic,
                                 @Value("${beemart.integration.web.kafka.product-topic-key-header}") String productPartitionKeyHeader) {
        this.kafkaTemplate = kafkaTemplate;
        this.noRootMapper = noRootMapper;
        this.productTopic = productTopic;
        this.productPartitionKeyHeader = productPartitionKeyHeader;
    }

    @RequestMapping(value = "/products", method = {PUT, POST})
    public void productListener(@RequestHeader(required = false) HashMap<String, Object> headers,
                                @RequestBody(required = false) JsonNode body) throws JsonProcessingException {
        if (body != null) {
            String key = null;
            String rawJsonBody = body.toString();
            log.info("Receive web product webhook " + rawJsonBody);
            val message = new KafkaMessageModel();
            message.setPayload(rawJsonBody);
            if (headers != null && headers.containsKey(productPartitionKeyHeader.toLowerCase())) {
                message.setHeaders(headers);
                key = String.valueOf(headers.get(productPartitionKeyHeader.toLowerCase()));
            }

            val msgStr = noRootMapper.writeValueAsString(message);

            kafkaTemplate.send(productTopic, key, msgStr).addCallback(sendResult -> {
            }, throwable -> log.error("error send " + msgStr, throwable));
        }
    }

}
