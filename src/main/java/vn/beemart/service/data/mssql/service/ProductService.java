package vn.beemart.service.data.mssql.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vn.beemart.service.common.validate.NotFoundException;
import vn.beemart.service.data.mssql.dao.*;
import vn.beemart.service.data.mssql.dto.*;
import vn.beemart.service.web.rest.model.image.ImageResponse;
import vn.beemart.service.web.rest.model.inventory.InventoryResponse;
import vn.beemart.service.web.rest.model.option.OptionResponse;
import vn.beemart.service.web.rest.model.product.ProductResponse;
import vn.beemart.service.web.rest.model.variant.VariantResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private VariantDao variantDao;
    private ProductDao productDao;
    private OptionDao optionDao;
    private ImageDao imageDao;
    private InventoryDao inventoryDao;
    private ObjectMapper objectMapper;

    @Autowired
    public ProductService(VariantDao variantDao
            , ProductDao productDao
            , OptionDao optionDao
            , ImageDao imageDao
            , InventoryDao inventoryDao
            , @Qualifier("json_with_root") ObjectMapper objectMapper) {
        this.variantDao = variantDao;
        this.productDao = productDao;
        this.optionDao = optionDao;
        this.imageDao = imageDao;
        this.inventoryDao = inventoryDao;
        this.objectMapper = objectMapper;
    }

    public ProductResponse getResponse(Integer productId) {

        val product = productDao.getById(productId);
        val response = objectMapper.convertValue(product, ProductResponse.class);

        imageDao.getByProductId(productId).forEach(
                (Image image) -> {
                    if (image != null) {
                        response.setImages(new ArrayList<>());
                        response.addImage(objectMapper.convertValue(image, ImageResponse.class));
                    }
                }
        );
        optionDao.getByProductId(productId)
                .forEach(
                        (Option option) -> {
                            response.addOption(objectMapper.convertValue(option, OptionResponse.class));
                        }
                );
        variantDao.getByProductId(productId)
                .forEach((Variant variant)
                                -> {
                            if (variant != null) {
                                response.addVariant(objectMapper.convertValue(variant, VariantResponse.class));
                            }
                        }

                );
        response.getVariants().forEach(
                (VariantResponse variant) -> {
                    inventoryDao.getByVariantId(variant.getId()).forEach(
                            (Inventory inventory) -> {
                                variant.addInventory(objectMapper.convertValue(inventory, InventoryResponse.class));

                            }
                    );
                }
        );


        return response;
    }

    public List<ProductResponse> getListResponse(List<Product> products) {
        val listProductResponses = new ArrayList<ProductResponse>();
        products.forEach(
                (Product product) -> {
                    listProductResponses.add(getResponse(product.getId()));
                }
        );
        return listProductResponses;
    }

    public List<Variant> getListVariants(List<Product> products) {
        val variants = new ArrayList<Variant>();
        products.forEach(
                (Product product) -> {
                    variants.addAll(variantDao.getByProductId(product.getId()));
                }
        );

        return variants;
    }

    public VariantResponse getVariantRes(Variant variant) {
            val variantResponse = objectMapper.convertValue(variant, VariantResponse.class);
            inventoryDao.getByVariantId(variant.getId()).forEach(
                    (Inventory inventory) -> {
                        variantResponse.addInventory(objectMapper.convertValue(inventory, InventoryResponse.class));

                    }
            );
            return variantResponse;
    }

    public List<VariantResponse> getListVariantsRes(List<Variant> variants) {
        val listVariantsResponse = new ArrayList<VariantResponse>();
        variants.forEach((Variant variant) -> {
            listVariantsResponse.add(getVariantRes(variant));
        });
        return listVariantsResponse;
    }

    public List<ProductResponse> getResponseFromVariantDto(List<VariantResponse> listVariant) {
        val listProduct = new ArrayList<ProductResponse>();
        listVariant.forEach(
                (VariantResponse variant) -> {
                    listProduct.add(getResponse(variant.getProductId()));
                }
        );
        return listProduct;
    }
}
