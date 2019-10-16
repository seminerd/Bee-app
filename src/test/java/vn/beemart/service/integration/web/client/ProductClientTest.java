package vn.beemart.service.integration.web.client;

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
        String token = "1939cd8db55743cc81f39495acdf97c4";
        var uri = new URI("https://beemart-dev.bizwebvietnam.net");
        var response = productClient.filter(uri, token, 1, 10);
        assert response != null;
    }
}
