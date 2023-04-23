package fun.riding4.transport.config.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public static ServiceException from(ErrorCode errorCode) {
        return new ServiceException(errorCode);
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}