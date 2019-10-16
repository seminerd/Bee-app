package vn.beemart.service.common.validate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Action is forbidden")
public class ForbiddenException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message = "Không có quyền hoặc bị chặn thao tác";

    public ForbiddenException(){

    }

    public ForbiddenException(String message){
        this.message= message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
