package com.pickban.global.error.exception;

import com.pickban.global.error.ErrorCode;

public class ExpiredRefreshTokenException extends BusinessException {
    public ExpiredRefreshTokenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ExpiredRefreshTokenException(String message) {
        super(message, ErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}
