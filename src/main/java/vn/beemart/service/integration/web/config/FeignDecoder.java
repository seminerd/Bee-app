package vn.beemart.service.integration.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.jackson.JacksonDecoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;

public class FeignDecoder extends JacksonDecoder {
    public FeignDecoder(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (type.equals(String.class)) {
            return IOUtils.toString(response.body().asReader());
        }
        return super.decode(response, type);
    }
}
