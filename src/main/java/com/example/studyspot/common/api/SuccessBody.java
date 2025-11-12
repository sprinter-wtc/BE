package com.example.studyspot.common.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class SuccessBody<D> {
    private final D data;
    private final String status;
}
