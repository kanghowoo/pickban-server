package com.pickban.global.error.exception;

import com.pickban.global.error.ErrorCode;

public class UserPasswordMatchFailedException extends BusinessException{
    public UserPasswordMatchFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
