package vn.beemart.service.web.rest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.common.controller.BaseController;
import vn.beemart.service.common.validate.BadRequestException;
import vn.beemart.service.data.mssql.dao.ProductDao;
import vn.beemart.service.data.mssql.dto.Product;
import vn.beemart.service.integration.pos.client.ProductClient;
import vn.beemart.service.integration.pos.model.Metadata;
import vn.beemart.service.web.rest.model.product.ListProductResponse;
import vn.beemart.service.web.rest.model.product.ProductRequest;
import vn.beemart.service.web.rest.model.product.ProductResponse;
import vn.beemart.service.web.rest.service.ProductFilter;
import vn.beemart.service.data.mssql.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CommonsLog
@RequestMapping("/products")


public class ProductController extends BaseController {
    public static final int PAGE_LIMIT = 250;

    private final ProductDao productDao;
    @Autowired
    ProductService service;
    @Autowired
    ProductFilter filter;
    private ObjectMapper objectMapper;
    private ProductClient productClient;

    public ProductController(ProductDao productDao,

                             @Qualifier("json_with_root") ObjectMapper objectMapper,
                             ProductClient productClient) {
        this.productDao = productDao;
        this.objectMapper = objectMapper;
        this.productClient = productClient;

    }


    @ApiOperation(value = "Get a product with ID")
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") int id) {
        val response = service.getResponse(id);

        return response;
    }

    @ApiOperation(value = "Filtering product")
    @GetMapping
    public ListProductResponse filteredProductResponse(@RequestParam(value = "brand", required = false) String brand
            , @RequestParam(value = "min_price", required = false) BigDecimal minPrice
            , @RequestParam(value = "max_price", required = false) BigDecimal maxPrice
            , @RequestParam(value = "location_id", required = false) Integer locationId
            , @RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "category_id", required = false) Integer categoryId) {


        val productResponseList = service.getListResponse(productDao.getAllWithPage(page));
        filter.setService(service);
        filter.setProductResponseList(productResponseList);
        if (categoryId != null) {
            filter.byCategoryId(categoryId);
        }
        if (brand != null) {
            filter.byBrand(brand);
        }

        if (minPrice != null && maxPrice != null) {
            filter.byMinPrice(minPrice).byMaxPrice(maxPrice);

        } else if (minPrice == null && maxPrice != null) {
            filter.byMaxPrice(maxPrice);

        } else if (minPrice != null && maxPrice == null) {
            filter.byMinPrice(minPrice);
        }
        if (locationId != null) {
            filter.byLocationId(locationId);
        }

        val listProduct = new ListProductResponse();

        listProduct.setProducts(filter.getFilteredList());
        listProduct.setMetadata(
                new Metadata(listProduct.getProducts().size(), page, PAGE_LIMIT)
        );


        return listProduct;
    }

}
