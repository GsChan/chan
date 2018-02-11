package com.gitsome.chan.core.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author : 130801(cgs)
 * Date : 2018-02-09
 * Time : 16:59
 */
public class WafException extends RuntimeException {
    ResponseEntity<ErrorMessage> responseEntity;

    public WafException(ResponseEntity<ErrorMessage> responseEntity, Throwable cause) {
        super(responseEntity.getBody().getMessage(), cause);
        this.responseEntity = responseEntity;
    }

    public WafException(ResponseEntity<ErrorMessage> responseEntity) {
        this(responseEntity, null);
    }

    public WafException(ErrorMessage errorMessage, HttpStatus status, Throwable cause) {
        this(new ResponseEntity<>(errorMessage, status), cause);
    }

    public WafException(ErrorMessage errorMessage, HttpStatus status) {
        this(new ResponseEntity<>(errorMessage, status));
    }

    public WafException(String code, String message, String detail, HttpStatus status, Throwable cause) {
        this(new ErrorMessage(code, message, detail), status, cause);
    }

    public WafException(String code, String message, String detail, HttpStatus status) {
        this(new ErrorMessage(code, message, detail), status, null);
    }

    public WafException(String code, String message, HttpStatus status, Throwable cause) {
        this(new ErrorMessage(code, message), status, cause);
    }

    public WafException(String code, String message, HttpStatus status) {
        this(code, message, status, null);
    }

    public WafException(String code, String message, Throwable cause) {
        this(new ErrorMessage(code, message), HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }

    public WafException(String code, String message) {
        this(code, message, (Throwable) null);
    }

    public ResponseEntity<ErrorMessage> getResponseEntity() {
        return responseEntity;
    }

    public ErrorMessage getError() {
        return responseEntity.getBody();
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
//        return I18NProvider.getString(getMessage());
    }
}
