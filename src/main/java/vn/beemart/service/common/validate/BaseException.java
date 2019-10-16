package vn.beemart.service.common.validate;

@SuppressWarnings("serial")
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException() {
        super("Exception");
    }
}
