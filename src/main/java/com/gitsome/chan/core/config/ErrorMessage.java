package com.gitsome.chan.core.config;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author : 130801(cgs)
 * Date : 2018-02-09
 * Time : 16:53
 */

public class ErrorMessage {


    private String code;
    private String message;
    private String detail;
    private ResponseErrorMessage cause;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ResponseErrorMessage getCause() {
        return cause;
    }

    public void setCause(ResponseErrorMessage cause) {
        this.cause = cause;
    }

    public ErrorMessage() {
    }

    public ErrorMessage(String code) {
        this(code, null, null);
    }

    public ErrorMessage(String code, String message) {
        this(code, message, null);
    }

    public ErrorMessage(String code, String message, String detail) {
        this(code, message, detail, null);
    }

    public ErrorMessage(String code, String message, String detail, ResponseErrorMessage cause) {
        this.message = StringUtils.isEmpty(message)?"null($WAF)":message;
        this.code    = code;
        this.detail  = detail;
        this.cause   = cause;
    }
}
