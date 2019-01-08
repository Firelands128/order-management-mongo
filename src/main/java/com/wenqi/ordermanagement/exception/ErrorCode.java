package com.wenqi.ordermanagement.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    CONFLICT(HttpStatus.CONFLICT),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

    ErrorCode(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    String getMessage() {
        switch (status) {
            case BAD_REQUEST:
                return "Bad Request.";
            case UNAUTHORIZED:
                return "Unauthorized";
            case FORBIDDEN:
                return "Forbidden";
            case NOT_FOUND:
                return "Not found";
            case CONFLICT:
                return "Conflict";
            case INTERNAL_SERVER_ERROR:
                return "Internal server error";
            default:
                return "";
        }
    }
}
