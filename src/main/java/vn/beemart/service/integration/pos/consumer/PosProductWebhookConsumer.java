package vn.beemart.service.integration.pos.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vn.beemart.service.common.annotation.ConditionalOnEnableJob;
import vn.beemart.service.common.model.KafkaMessageModel;
import vn.beemart.service.integration.pos.model.dto.*;
import vn.beemart.service.integration.pos.model.repository.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@ConditionalOnEnableJob
@CommonsLog
@Component
@Transactional
public class PosProductWebhookConsumer {
    private PosProductRepository productRepository;
    private PosVariantRepository variantRepository;
    private PosVariantPriceRepository variantPriceRepository;
    private PosImageRepository imageRepository;
    private PosCompositeItemRepository compositeItemRepository;
    private PosOptionRepository optionRepository;
    private PosInventoryRepository inventoryRepository;
    private ObjectMapper objectMapper;

    public PosProductWebhookConsumer(PosProductRepository productRepository
            , PosVariantRepository variantRepository
            , PosVariantPriceRepository variantPriceRepository
            , PosImageRepository imageRepository
            , PosOptionRepository optionRepository
            , PosPriceListRepository priceListRepository
            , PosInventoryRepository inventoryRepository
            , PosCompositeItemRepository compositeItemRepository
            , @Qualifier("json_without_root") ObjectMapper objectMapper) {
        this.variantPriceRepository = variantPriceRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.optionRepository = optionRepository;
        this.compositeItemRepository = compositeItemRepository;
        this.imageRepository = imageRepository;
        this.variantRepository = variantRepository;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "${beemart.integration.pos.kafka.product-topic}",
            concurrency = "${beemart.integration.pos.kafka.product-consumer-concurrency:1}",
            groupId = "${beemart.integration.pos.kafka.product-consumer-group-id}"
    )
    public void processProductTopic(KafkaMessageModel messageModel) throws IOException {
        log.info("Message payload from Pos/product " + messageModel.getPayload());
        val product = objectMapper.readValue(messageModel.getPayload(), PosProduct.class);
        List<PosVariant> listVariants = product.getVariants();
        List<PosOption> listOption = product.getOptions();
        List<PosImage> listImage = product.getImages();

        listVariants.parallelStream().map(variant -> {
            compositeItemRepository.saveAll(variant.getCompositeItems());
            inventoryRepository.saveAll(variant.getInventories());
            variantPriceRepository.saveAll(variant.getVariantPrices());
            return null;
        }).close();
        productRepository.save(product);
        imageRepository.saveAll(listImage);
        optionRepository.saveAll(listOption);
        variantRepository.saveAll(listVariants);


    }

    @KafkaListener(topics = "${beemart.integration.pos.kafka.inventory-topic}",
            concurrency = "${beemart.integration.pos.kafka.product-consumer-concurrency:1}",
            groupId = "${beemart.integration.pos.kafka.product-consumer-group-id}"
    )
    public void processInventoryTopic(KafkaMessageModel messageModel) throws IOException {
        log.info("Message payload from Pos/inventory " + messageModel.getPayload());
        val inventory = objectMapper.readValue(messageModel.getPayload(), PosInventory.class);
        inventoryRepository.save(inventory);

    }

}
