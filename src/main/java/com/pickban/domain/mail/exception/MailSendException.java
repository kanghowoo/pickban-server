package com.pickban.domain.mail.exception;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.error.exception.BusinessException;

public class MailSendException extends BusinessException {
    public MailSendException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MailSendException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
