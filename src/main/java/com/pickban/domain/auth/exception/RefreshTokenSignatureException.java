package com.pickban.domain.auth.exception;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.error.exception.BusinessException;

public class RefreshTokenSignatureException extends BusinessException {
    public RefreshTokenSignatureException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RefreshTokenSignatureException(String message) {
        super(message, ErrorCode.REFRESH_TOKEN_VERIFICATION_FAILED);
    }
}
