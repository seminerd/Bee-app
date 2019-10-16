package vn.beemart.service.integration.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.integration.web.config.WebFeignClientConfig;
import vn.beemart.service.integration.web.model.ProductListResponse;
import vn.beemart.service.integration.web.model.ProductResponse;

import java.net.URI;

@FeignClient(value = "web-product-client",
        url = "url-place-holder",
        configuration = WebFeignClientConfig.class)
public interface ProductClient {

    @RequestMapping(value = "/admin/products/{id}.json", method = RequestMethod.GET)
    ProductResponse getById(URI baseUrl, @RequestHeader("X-Bizweb-Access-Token") String token, @PathVariable("id") int id);
    @RequestMapping(value = "/admin/products.json", method = RequestMethod.GET)
    ProductListResponse filter(URI baseUrl, @RequestHeader("X-Bizweb-Access-Token") String token, @RequestParam("page")int page, @RequestParam("limit") int limit);


}
