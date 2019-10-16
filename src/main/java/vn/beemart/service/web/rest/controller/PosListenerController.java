package vn.beemart.service.web.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.beemart.service.common.model.KafkaMessageModel;
import vn.beemart.service.data.mssql.dto.Product;
import vn.beemart.service.integration.pos.model.dto.PosInventory;
import vn.beemart.service.integration.pos.model.dto.PosProduct;

import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CommonsLog
@RestController
@RequestMapping("/listeners/pos")
public class PosListenerController {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String productTopic;
    private String productPartitionKeyHeader;
    private String orderTopic;
    private String orderPartitionKeyHeader;
    private ObjectMapper objectMapper;
    private String inventoryTopic;


    public PosListenerController(KafkaTemplate<String, String> kafkaTemplate,
                                 @Qualifier("json_without_root") ObjectMapper objectMapper,
                                 @Value("${beemart.integration.pos.kafka.product-topic}") String productTopic,
                                 @Value("${beemart.integration.pos.kafka.inventory-topic}") String inventoryTopic,
                                 @Value("${beemart.integration.pos.kafka.product-topic-key-header:}") String productPartitionKeyHeader,
                                 @Value("${beemart.integration.pos.kafka.order-topic}") String orderTopic,
                                 @Value("${beemart.integration.pos.kafka.order-topic-key-header:}") String orderPartitionKeyHeader) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.productTopic = productTopic;
        this.productPartitionKeyHeader = productPartitionKeyHeader;
        this.inventoryTopic = inventoryTopic;
        this.orderTopic = orderTopic;
        this.orderPartitionKeyHeader = orderPartitionKeyHeader;
    }


    @RequestMapping(value = "/products", method = {PUT, POST})
    public void productListener(@RequestHeader(required = false) HashMap<String, Object> headers,
                                @RequestBody(required = false) JsonNode body) throws JsonProcessingException {
        if (body != null) {
            log.info(" Receive pos product webhook " + body.toString());
            forwardWebhookToKafka(headers, objectMapper.convertValue(body, JsonNode.class), productPartitionKeyHeader, productTopic);
        }
    }

    @RequestMapping(value = "/orders", method = {PUT, POST})
    public void orderListener(@RequestHeader(required = false) HashMap<String, Object> headers,
                              @RequestBody(required = false) JsonNode body) throws JsonProcessingException {
        if (body != null) {
            log.info(" Receive pos order webhook " + body.toString());
            forwardWebhookToKafka(headers, body, orderPartitionKeyHeader, orderTopic);
        }
    }

    private void forwardWebhookToKafka(HashMap<String, Object> headers, JsonNode body, String keyHeader, String topic) throws JsonProcessingException {
        String key = null;
        String rawJsonBody = body.toString();
        val message = new KafkaMessageModel();
        message.setPayload(rawJsonBody);
        if (headers != null && headers.containsKey(keyHeader.toLowerCase())) {
            message.setHeaders(headers);
            key = String.valueOf(headers.get(keyHeader.toLowerCase()));
        }

        val msgStr = objectMapper.writeValueAsString(message);


        kafkaTemplate.send(topic, key, msgStr).addCallback(sendResult -> {
        }, throwable -> log.error("error send " + msgStr, throwable));

    }

    @RequestMapping(value = "/inventory", method = {PUT, POST})
    public void inventoryListener(@RequestHeader(required = false) HashMap<String, Object> headers,
                                  @RequestBody(required = false) PosInventory body) throws JsonProcessingException {
        if (body != null) {
            forwardWebhookToKafka(headers, objectMapper.convertValue(body, JsonNode.class), null, inventoryTopic);
        }


    }
}
