package com.pickban.common.error.exception;

import com.pickban.common.error.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public UserAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
