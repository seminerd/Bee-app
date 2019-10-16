package vn.beemart.service.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.beemart.service.common.validate.CustomError;
import vn.beemart.service.common.validate.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@CommonsLog(topic = "exception")
public class BaseController {
    @Autowired
    protected MapperFacade mapperFacade;


    @ExceptionHandler(FormValidateException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidateMessage handleFormValidateException(FormValidateException ex, HttpServletRequest request) {
        val exceptionMessage = new ValidateMessage();
        Map<String, Object> errors = new HashMap<>();
        exceptionMessage.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        if (ex.getBindingResult() != null) {
            for (val error : ex.getBindingResult().getFieldErrors()) {
                putFieldError(errors, error);
            }
        }
        if (ex.getMessageResult() != null) {
            for (val error : ex.getMessageResult().entrySet()) {
                val key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, error.getKey());
                val value = error.getValue();
                if (!errors.containsKey(key)) {
                    errors.put(key, value);
                }
            }
        }
        exceptionMessage.setErrors(errors);
        return exceptionMessage;
    }

    private void putFieldError(Map<String, Object> errors, FieldError error) {
        val key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, error.getField());
        val value = error.getDefaultMessage();
        if (!errors.containsKey(key)) {
            errors.put(key, value.toString());
        }
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public CustomError handleNotFoundException(HttpServletResponse response, NotFoundException e) {
        response.setContentType("application/json");
        response.setHeader("Content-Type", "application/json");
        // response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return new CustomError(e.getMessage());
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public CustomError handleTypeMismatchException(HttpServletResponse response) {
        response.setContentType("application/json");
        return new CustomError("Sai kiểu dữ liệu");
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public CustomError handleForbiddenException(HttpServletResponse response, ForbiddenException exception) {
        response.setContentType("application/json");
        return new CustomError(exception.getMessage());

    }

    @ExceptionHandler(ProhibitiveException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public CustomError handleProhibitiveException(HttpServletResponse response, ProhibitiveException exception) {
        response.setContentType("application/json");
        return new CustomError(exception.getMessage());

    }

    @ExceptionHandler(ServerErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError handleServerErrorException(HttpServletResponse response, ServerErrorException exception) {
        response.setContentType("application/json");
        return new CustomError(exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public CustomError handleUnauthorizedException(HttpServletResponse response) {
        response.setContentType("application/json");
        return new CustomError("Thông tin xác thực không chính xác");
    }
}
