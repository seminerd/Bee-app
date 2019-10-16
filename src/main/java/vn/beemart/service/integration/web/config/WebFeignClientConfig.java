package vn.beemart.service.integration.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;

public class WebFeignClientConfig {

    private ObjectMapper jsonRoot;

    public WebFeignClientConfig(@Qualifier("json_with_root")ObjectMapper jsonRoot) {
        this.jsonRoot = jsonRoot;
    }

    @Bean
    public RequestInterceptor feignRequestInterceptorWeb() {
        return template -> {
            template.header("Content-Type", "application/json; charset=utf-8");
        };
    }

    @Bean
    public Decoder feignDecoderWeb() {
        return new ResponseEntityDecoder(new FeignDecoder(jsonRoot));
    }

    @Bean
    public Encoder feignEncoderWeb() {
        return new JacksonEncoder(jsonRoot);
    }

}