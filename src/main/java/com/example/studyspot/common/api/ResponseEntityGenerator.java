package com.example.studyspot.common.api;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseEntityGenerator {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    public static <D> ResponseEntity<SuccessBody<D>> success(D data, HttpStatus status) {
        return new ResponseEntity<> (
                new SuccessBody<>(data, SUCCESS), status);
    }

    public static ResponseEntity<SuccessBody<Void>> success(HttpStatus status) {
        return success(null, status);
    }

    public static ResponseEntity<ErrorBody> error(String errorCode, String errorMessage, HttpStatus status ) {
        return new ResponseEntity<>(
                new ErrorBody(ERROR, errorCode, errorMessage), status);
    }
}
