package vn.beemart.service.integration.pos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.integration.pos.config.FeignClientConfig;
import vn.beemart.service.integration.pos.model.PosAddOrderRequest;
import vn.beemart.service.integration.pos.model.PosOrderResponse;
import vn.beemart.service.integration.web.config.WebFeignClientConfig;

import java.net.URI;

@FeignClient(value = "pos-order-client", url = "url-place-holder", configuration = FeignClientConfig.class)
public interface OrderClient {
    @RequestMapping(value = "/admin/orders.json", method = RequestMethod.POST)
    PosOrderResponse addOrder(URI baseUrl, @RequestHeader("X-Sapo-Access-Token") String token, @RequestHeader("X-Sapo-LocationId") String locationId, PosAddOrderRequest model);
}
