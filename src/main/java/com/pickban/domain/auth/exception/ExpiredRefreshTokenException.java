package com.pickban.domain.auth.exception;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.error.exception.BusinessException;

public class ExpiredRefreshTokenException extends BusinessException {
    public ExpiredRefreshTokenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ExpiredRefreshTokenException(String message) {
        super(message, ErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}
