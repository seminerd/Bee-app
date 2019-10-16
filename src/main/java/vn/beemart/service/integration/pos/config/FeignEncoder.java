package vn.beemart.service.integration.pos.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.jackson.JacksonEncoder;
import vn.beemart.service.integration.pos.model.BaseIgnoreRootJsonModel;

import java.lang.reflect.Type;

public class FeignEncoder extends JacksonEncoder {
    private ObjectMapper jsonRoot;
    private ObjectMapper jsonWithoutRoot;

    public FeignEncoder(ObjectMapper jsonRoot, ObjectMapper jsonWithoutRoot) {
        this.jsonRoot = jsonRoot;
        this.jsonWithoutRoot = jsonWithoutRoot;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        if (BaseIgnoreRootJsonModel.class.isAssignableFrom((Class) bodyType)) {
            encode(object, bodyType, template, jsonWithoutRoot);
        } else {
            encode(object, bodyType, template, jsonRoot);
        }
    }

    private void encode(Object object, Type bodyType, RequestTemplate template, ObjectMapper mapper) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructType(bodyType);
            template.body(mapper.writerFor(javaType).writeValueAsString(object));
        } catch (JsonProcessingException e) {
            throw new EncodeException(e.getMessage(), e);
        }
    }
}
