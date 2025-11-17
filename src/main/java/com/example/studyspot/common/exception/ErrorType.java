package com.example.studyspot.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorType {

    public HttpStatus getHttpStatus();

    public String getMessage();

    public String getErrorCode();
}
