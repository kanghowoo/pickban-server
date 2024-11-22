package com.pickban.common.error.exception;

import com.pickban.common.error.ErrorCode;

public class MailSendException extends BusinessException{
    public MailSendException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MailSendException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
