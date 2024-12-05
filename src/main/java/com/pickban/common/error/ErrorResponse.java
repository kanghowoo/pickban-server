package com.pickban.common.error;

import com.pickban.common.response.Alert;

import lombok.Getter;

@Getter
public class ErrorResponse {
    protected String message;
    protected String code;
    protected Alert alert;

    public ErrorResponse() {
    }

    protected ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    protected ErrorResponse(String message, String code, Alert alert) {
        this.message = message;
        this.code = code;
        this.alert = alert;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getCode());
    }

    public static ErrorResponse of(ErrorCode errorCode, Alert alert) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getCode(), alert);
    }

}
