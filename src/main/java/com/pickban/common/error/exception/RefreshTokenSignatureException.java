package com.pickban.common.error.exception;

import com.pickban.common.error.ErrorCode;

public class RefreshTokenSignatureException extends BusinessException {
    public RefreshTokenSignatureException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RefreshTokenSignatureException(String message) {
        super(message, ErrorCode.REFRESH_TOKEN_VERIFICATION_FAILED);
    }
}
