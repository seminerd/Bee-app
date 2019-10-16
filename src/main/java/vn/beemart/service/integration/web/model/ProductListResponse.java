package vn.beemart.service.integration.web.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;

@JsonRootName("products")
public class ProductListResponse extends ArrayList<ProductResponse> {
    private static final long serialVersionUID = 1L;
}
