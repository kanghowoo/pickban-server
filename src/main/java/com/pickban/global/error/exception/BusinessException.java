package com.pickban.global.error.exception;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.web.dto.Alert;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Alert alert;

    public BusinessException(ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode, null);
    }

    public BusinessException(String message, ErrorCode errorCode) {
        this(message, errorCode, null);
    }

    public BusinessException(String message, ErrorCode errorCode, Alert alert) {
        super(message);
        this.errorCode = errorCode;
        this.alert = alert;
    }

}
