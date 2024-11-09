package com.pickban.common.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    protected String message;
    protected String code;

    public ErrorResponse() {
    }

    protected ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getCode());
    }

}
