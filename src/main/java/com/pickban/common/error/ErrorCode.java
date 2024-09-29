package com.pickban.common.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
@Getter
public enum ErrorCode {
    /** user errors **/
    NOT_VERIFIED_USER("U001", "not verified user", FORBIDDEN),
    EMAIL_ALREADY_EXIST("U002", "email already exist", CONFLICT),
    NICKNAME_ALREADY_EXIST("U003", "nickname already exist", CONFLICT),
    USER_VERIFY_FAILED("U004", "user verify failed", NOT_FOUND),
    USER_EMAIL_SEND_RATE_LIMITED("U005", "user email send rate limited", CONFLICT),
    PASSWORD_MATCH_FAILED("U006", "user password match failed", UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
