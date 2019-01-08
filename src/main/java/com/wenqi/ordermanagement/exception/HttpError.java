package com.wenqi.ordermanagement.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class HttpError {
    public final int status;
    public final String code;
    public final String message;
    public final String debug;

    HttpError(IOrderException e) {
        this.status = e.getErrorCode().getStatus().value();
        this.code = e.getErrorCode().name();
        this.message = e.getMessage();
        this.debug = e.getCause() == null ? "" : e.getCause().getMessage();
    }
}
