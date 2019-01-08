package com.wenqi.ordermanagement.exception;

public class IOrderException extends RuntimeException {
    private final ErrorCode errorCode;

    public IOrderException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public IOrderException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public IOrderException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public IOrderException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
