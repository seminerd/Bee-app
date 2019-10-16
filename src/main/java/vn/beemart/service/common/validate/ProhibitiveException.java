package vn.beemart.service.common.validate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Action is forbidden")
public class ProhibitiveException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message = "Gói dịch vụ của bạn không được phép sử dụng tính năng";

    public ProhibitiveException(){

    }

    public ProhibitiveException(String message){
        this.message= message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
