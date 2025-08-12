package com.pickban.global.error.exception;

import com.pickban.global.error.ErrorCode;

public class AuthorizationFailedException extends BusinessException {
    public AuthorizationFailedException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthorizationFailedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
