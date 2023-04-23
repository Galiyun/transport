package fun.riding4.transport.config.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_PARAMETER("400_001", "Invalid parameter", HttpStatus.BAD_REQUEST),
    FILE_NOT_FOUND("404_001", "File not found", HttpStatus.NOT_FOUND),

    INTERNAL_SERVER_ERROR("500_001", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
