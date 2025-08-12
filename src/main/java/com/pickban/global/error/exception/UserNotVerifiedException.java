package com.pickban.global.error.exception;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.web.dto.Alert;

public class UserNotVerifiedException extends BusinessException{
    public UserNotVerifiedException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode, Alert.of("이메일 인증을 완료해야 서비스를 이용하실 수 있습니다."));
    }

}
