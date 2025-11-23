package com.example.studyspot.auth.exception;

import com.example.studyspot.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum AuthErrorType implements ErrorType {
    EXPIRED_TOKEN("AUTH-0001", HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다"),
    NOT_VALID_TOKEN("AUTH-0002", HttpStatus.BAD_REQUEST, "토큰값이 잘못되었습니다"),
    NULL_TOKEN_EXCEPTION("AUTH-0003", HttpStatus.BAD_REQUEST, "토큰이 null 입니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    AuthErrorType(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
