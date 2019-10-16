package vn.beemart.service.configuration;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Custom Http Message converter
 */
public class CustomHttpMessageConverter implements HttpMessageConverter<Object> {
    private MappingJackson2HttpMessageConverter mainMessageConverter;
    private MappingJackson2HttpMessageConverter jsonConverter;
    private MappingJackson2HttpMessageConverter orionMessageConverter;
    private ObjectMapper mainObjectMapper;
    private ObjectMapper jsonObjectMapper;
    private final List<String> METRICS = Arrays.asList("actuator");

    public CustomHttpMessageConverter(ObjectMapper jsonWithRoot, ObjectMapper jsonWithoutRoot) {
        mainMessageConverter = new MappingJackson2HttpMessageConverter();
        mainMessageConverter.setObjectMapper(jsonWithRoot);
        jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(jsonWithoutRoot);
        orionMessageConverter = new MappingJackson2HttpMessageConverter();
        orionMessageConverter.setObjectMapper(new ObjectMapper());
        mainObjectMapper = jsonWithRoot;
        jsonObjectMapper = jsonWithoutRoot;
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return mainMessageConverter.canRead(clazz, mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return true;

    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return mainMessageConverter.getSupportedMediaTypes();
    }

    @Override
    public Object read(Class<?> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        if (clazz.isAnnotationPresent(JsonRootName.class)) {
            String data = IOUtils.toString(inputMessage.getBody(), "UTF-8");
            return mainObjectMapper.readValue(data, clazz);
        } else {
            String data = IOUtils.toString(inputMessage.getBody(), "UTF-8");
            return jsonObjectMapper.readValue(data, clazz);
        }

    }

    @Override
    public void write(Object object, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        HttpMessageConverter<Object> httpMessageConverter = getMessageConverter(object);
        httpMessageConverter.write(object, contentType, outputMessage);
    }

    /**
     * Used to find message converter that will be used for serialization
     *
     * @return Message converter
     */
    private HttpMessageConverter<Object> getMessageConverter(Object object) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        boolean isMetric = false;
        for (String metric : METRICS) {
            if (request.getRequestURI().startsWith("/" + metric)) {
                isMetric = true;
                break;
            }
        }
        if (isMetric) {
            return orionMessageConverter;
        }

        if (object.getClass().isAnnotationPresent(JsonRootName.class)) {
            return mainMessageConverter;
        }

        return jsonConverter;
    }
}