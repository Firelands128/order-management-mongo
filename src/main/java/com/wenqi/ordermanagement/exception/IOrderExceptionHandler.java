package com.wenqi.ordermanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class IOrderExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({IOrderException.class})
    @ResponseBody
    public ResponseEntity<HttpError> handleIOrderException(IOrderException e) {
        logger.debug("IOrder Exception handler ", e);
        return new ResponseEntity<>(new HttpError(e), e.getErrorCode().getStatus());
    }
}
