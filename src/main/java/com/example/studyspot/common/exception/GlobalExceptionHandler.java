package com.example.studyspot.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudySpotException.class)
    private ResponseEntity<String> handleStudySpotException(StudySpotException ex) {
        ErrorType errorType = ex.getErrorType();
        String errorCode = errorType.getErrorCode();
        String errorMessage = errorType.getMessage();
        HttpStatus httpStatus = errorType.getHttpStatus();

        log.error("예외 발생! 예외 코드 : {}. 예외 메세지 : {}", errorCode, errorMessage );
        return ResponseEntity.status(httpStatus).body("errorCode : " + errorCode + "\n" + "errorMessage : " + errorMessage);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> handleException(Exception ex) {
        log.error("예상치 못한 예외 발생! 예외 : {}", ex);
        return ResponseEntity.internalServerError().body("예상치 못한 문제가 발생했습니다.");
    }
}
