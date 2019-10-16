package vn.beemart.service.integration.sync.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vn.beemart.service.common.annotation.ConditionalOnEnableJob;
import vn.beemart.service.common.model.KafkaMessageModel;
import vn.beemart.service.integration.sync.model.dto.SyncModel;
import vn.beemart.service.integration.sync.model.repository.SyncDataRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@ConditionalOnEnableJob
@CommonsLog
@Component
public class ProductSyncingWebhooksConsumer {
    @Autowired
    @Qualifier("json_without_root")
    ObjectMapper objectMapper;
    @Autowired
    SyncDataRepository syncDataRepository;

    @KafkaListener(topics = "${beemart.integration.sync.kafka.product-topic}",
            concurrency = "${beemart.integration.sync.kafka.product-consumer-concurrency:1}",
            groupId = "${beemart.integration.sync.kafka.product-consumer-group-id}"
    )

    @Transactional
    public void onMessage(KafkaMessageModel messageModel) throws IOException {
        log.info("Message payload from Pos/product " + messageModel.getPayload());
        val syncData = objectMapper.readValue(messageModel.getPayload(), SyncModel.class);
        syncDataRepository.save(syncData);


    }
}
