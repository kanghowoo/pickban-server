package com.pickban.common.error.exception;

import com.pickban.common.error.ErrorCode;

public class UserPasswordMatchFailedException extends BusinessException{
    public UserPasswordMatchFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
