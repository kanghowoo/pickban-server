package com.pickban.common.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;
@Getter
public enum ErrorCode {
    /** common errors **/
    ENTITY_NOT_FOUND("C001", "entity not found", NOT_FOUND),

    /** auth errors **/
    NEED_AUTHENTICATION("AU001", "need authentication", UNAUTHORIZED),
    AUTHORIZATION_FAILED("AU002", "authorization failed", FORBIDDEN),
    LOGIN_AUTHENTICATION_FAILED("AU003", "login authentication failed", UNAUTHORIZED),
    TOKEN_EXPIRED("AU004", "token is expired", UNAUTHORIZED),
    TOKEN_MALFORMED("AU005", "token is malformed", BAD_REQUEST),
    TOKEN_VERIFICATION_FAILED("AU006", "token verification failed", BAD_REQUEST),
    TOKEN_INTERNAL_ERROR("AU007", "token internal error", UNAUTHORIZED),
    REFRESH_TOKEN_EXPIRED("AU008", "refresh token is expired", UNAUTHORIZED),
    REFRESH_TOKEN_VERIFICATION_FAILED("AU009", "refresh token verification failed", BAD_REQUEST),

    /** user errors **/
    NOT_VERIFIED_USER("U001", "not verified user", FORBIDDEN),
    EMAIL_ALREADY_EXIST("U002", "email already exist", CONFLICT),
    NICKNAME_ALREADY_EXIST("U003", "nickname already exist", CONFLICT),
    USER_VERIFY_FAILED("U004", "user verify failed", NOT_FOUND),
    USER_EMAIL_SEND_RATE_LIMITED("U005", "user email send rate limited", TOO_MANY_REQUESTS),
    PASSWORD_MATCH_FAILED("U006", "user password match failed", UNAUTHORIZED);

    @Getter
    private final String code;
    @Getter
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }

}
