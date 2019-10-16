package vn.beemart.service.integration.pos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.integration.pos.config.FeignClientConfig;
import vn.beemart.service.integration.pos.model.ProductListResponse;
import vn.beemart.service.integration.pos.model.ProductResponse;

import java.net.URI;

@FeignClient(value = "pos-product-client",
        url = "url-place-holder",
        configuration = FeignClientConfig.class)
public interface ProductClient {

    @RequestMapping(value = "/admin/products/{id}.json", method = RequestMethod.GET)
    ProductResponse getById(URI baseUrl, @RequestHeader("X-Sapo-Access-Token") String token, @PathVariable("id") int id);
    @RequestMapping(value = "/admin/products.json", method = RequestMethod.GET)
    ProductListResponse filter(URI baseUrl, @RequestHeader("X-Sapo-Access-Token") String token, @RequestParam("page")int page, @RequestParam("limit") int limit);
}
