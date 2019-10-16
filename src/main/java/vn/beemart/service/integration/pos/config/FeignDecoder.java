package vn.beemart.service.integration.pos.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import feign.Response;
import feign.Util;
import feign.jackson.JacksonDecoder;
import vn.beemart.service.integration.pos.model.BaseIgnoreRootJsonModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

public class FeignDecoder extends JacksonDecoder {
    private ObjectMapper jsonWithoutRoot;
    private ObjectMapper jsonRoot;

    public FeignDecoder(ObjectMapper jsonRoot, ObjectMapper jsonWithoutRoot) {
        this.jsonWithoutRoot = jsonWithoutRoot;
        this.jsonRoot = jsonRoot;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (BaseIgnoreRootJsonModel.class.isAssignableFrom((Class) type)) {
            return decode(response, type, jsonWithoutRoot);
        }
        return decode(response, type, jsonRoot);
    }

    private Object decode(Response response, Type type, ObjectMapper mapper) throws IOException {
        if (response.status() == 404) return Util.emptyValueOf(type);
        if (response.body() == null) return null;
        Reader reader = response.body().asReader();
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader, 1);
        }
        try {
            // Read the first byte to see if we have any data
            reader.mark(1);
            if (reader.read() == -1) {
                return null; // Eagerly returning null avoids "No content to map due to end-of-input"
            }
            reader.reset();
            return mapper.readValue(reader, mapper.constructType(type));
        } catch (RuntimeJsonMappingException e) {
            if (e.getCause() != null && e.getCause() instanceof IOException) {
                throw IOException.class.cast(e.getCause());
            }
            throw e;
        }
    }
}