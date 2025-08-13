package com.pickban.domain.mail.exception;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.error.exception.BusinessException;
import com.pickban.global.web.dto.Alert;

public class UserEmailSendRateLimitException extends BusinessException {
    public UserEmailSendRateLimitException(ErrorCode errorCode) {
        super(errorCode.getMessage(),
              errorCode,
              Alert.of("이메일 발송 횟수 초과로 24시간 후에 재시도 가능합니다."));
    }
}
