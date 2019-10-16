package vn.beemart.service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;

import java.util.Arrays;

@Configuration
public class BeanConfig {

    @Bean(name = "converters_mains")
    @Primary
    public HttpMessageConverters convertersMain(@Qualifier("json_with_root") ObjectMapper jsonWithRoot, @Qualifier("json_without_root") ObjectMapper jsonWithoutRoot) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        return new HttpMessageConverters(false, Arrays.asList(
                new ByteArrayHttpMessageConverter(),
                stringHttpMessageConverter,
                new ResourceHttpMessageConverter(),
                new SourceHttpMessageConverter<>(),
                new AllEncompassingFormHttpMessageConverter(),
                new CustomHttpMessageConverter(jsonWithRoot, jsonWithoutRoot)
        ));
    }

    @Bean
    @Qualifier("mapper_facade")
    public MapperFacade mapper() {
        val defaultFactory = new DefaultMapperFactory.Builder().build();
        return defaultFactory.getMapperFacade();
    }

}
