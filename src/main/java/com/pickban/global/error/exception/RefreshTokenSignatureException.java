package com.pickban.global.error.exception;

import com.pickban.global.error.ErrorCode;

public class RefreshTokenSignatureException extends BusinessException {
    public RefreshTokenSignatureException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RefreshTokenSignatureException(String message) {
        super(message, ErrorCode.REFRESH_TOKEN_VERIFICATION_FAILED);
    }
}
