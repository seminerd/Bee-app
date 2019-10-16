package vn.beemart.service.integration.pos.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


@Configuration
public class FeignClientConfig {

    private ObjectMapper jsonWithoutRoot;
    private ObjectMapper jsonRoot;

    public FeignClientConfig(@Qualifier("json_with_root") ObjectMapper jsonRoot, @Qualifier("json_without_root") ObjectMapper jsonWithoutRoot) {
        this.jsonWithoutRoot = jsonWithoutRoot;
        this.jsonRoot = jsonRoot;
    }

    @Bean
    @Primary
    public RequestInterceptor feignRequestInterceptorDefault() {
        return template -> {
            template.header("Content-Type", "application/json; charset=utf-8");
            template.header("X-Sapo-ServiceId", "beemart-service");
            template.header("X-Sapo-Client", "beemart-service");
        };
    }

    @Bean
    @Primary
    public Decoder feignDecoderDefault() {
        return new ResponseEntityDecoder(new FeignDecoder(jsonRoot, jsonWithoutRoot));
    }

    @Bean
    @Primary
    public Encoder feignEncoderDefault() {
        return new FeignEncoder(jsonRoot, jsonWithoutRoot);
    }

    @Bean
    @Primary
    public Contract contractDefault() {
        return new SpringMvcContract();
    }

    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    @Profile({"debug"})
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    @Profile({"debug"})
    public Logger logger() {
        return new Slf4jLogger("service");
    }


}
