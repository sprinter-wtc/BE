package com.example.studyspot.common.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorBody {
    private final String status;
    private final String errorCode;
    private final String message;
}
