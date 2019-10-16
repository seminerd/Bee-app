package vn.beemart.service.integration.pos.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.beemart.service.Application;

import java.net.URI;
import java.net.URISyntaxException;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ImportAutoConfiguration({Application.class})
public class ProductClientTest {

    @Autowired
    private ProductClient productClient;

    @Test
    public void getProduct() throws URISyntaxException {
        String token = "7ac82024eb404fc59b20f3abc40df271";
        var uri = new URI("https://beemart-dev.mysapo.vn");
        var response = productClient.filter(uri, token, 1, 10);
        assert response != null;
    }
}
