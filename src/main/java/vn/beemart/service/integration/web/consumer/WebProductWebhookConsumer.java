package vn.beemart.service.integration.web.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vn.beemart.service.common.annotation.ConditionalOnEnableJob;
import vn.beemart.service.common.model.KafkaMessageModel;
import vn.beemart.service.integration.web.model.dto.WebImage;
import vn.beemart.service.integration.web.model.dto.WebOption;
import vn.beemart.service.integration.web.model.dto.WebProduct;
import vn.beemart.service.integration.web.model.dto.WebVariant;
import vn.beemart.service.integration.web.model.repository.WebImageRepository;
import vn.beemart.service.integration.web.model.repository.WebOptionRepository;
import vn.beemart.service.integration.web.model.repository.WebProductRepository;
import vn.beemart.service.integration.web.model.repository.WebVariantRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@CommonsLog
@ConditionalOnEnableJob
@Component
public class WebProductWebhookConsumer {

    private ObjectMapper objectMapper;
    private WebImageRepository imageRepository;
    private WebProductRepository productRepository;
    private WebOptionRepository optionRepository;
    private WebVariantRepository variantRepository;

    public WebProductWebhookConsumer(@Qualifier("json_without_root") ObjectMapper objectMapper,
                                     WebImageRepository imageRepository,
                                     WebProductRepository productRepository,
                                     WebOptionRepository optionRepository,
                                     WebVariantRepository variantRepository) {
        this.objectMapper = objectMapper;
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
        this.variantRepository = variantRepository;
        this.optionRepository = optionRepository;

    }

    @KafkaListener(topics = "${beemart.integration.web.kafka.product-topic}",
            concurrency = "${beemart.integration.web.kafka.product-consumer-concurrency:1}",
            groupId = "${beemart.integration.web.kafka.product-consumer-group-id}"
    )
    @Transactional
    public void onMessage(KafkaMessageModel messageModel) throws IOException {
        log.info(messageModel.getPayload());
        val product = objectMapper.readValue(messageModel.getPayload(), WebProduct.class);
        val variants = product.getVariants();
        val images = product.getImages();
        val options = product.getOptions();
        productRepository.save(product);
        variantRepository.saveAll(variants);
        imageRepository.saveAll(images);
        optionRepository.saveAll(options);


    }

}
