package vn.beemart.service.integration.pos.consumer;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vn.beemart.service.common.annotation.ConditionalOnEnableOrderJob;
import vn.beemart.service.common.model.KafkaMessageModel;

import java.io.IOException;

@ConditionalOnEnableOrderJob
@CommonsLog
@Component
public class PosOrderWebhookConsumer {

    @KafkaListener(topics = "${beemart.integration.pos.kafka.order-topic}",
            concurrency = "${beemart.integration.pos.kafka.order-consumer-concurrency:1}",
            groupId = "${beemart.integration.pos.kafka.order-consumer-group-id}"
    )
    public void onMessage(KafkaMessageModel messageModel) throws IOException {
        log.info("Message payload from Pos/order " + messageModel.getPayload());
    }
}
