package com.pickban.global.error.exception;

import com.pickban.global.error.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public UserAlreadyExistException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }

}
