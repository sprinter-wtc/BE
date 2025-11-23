package com.example.studyspot.auth.controller;

import com.example.studyspot.auth.dto.AccessToken;
import com.example.studyspot.auth.dto.request.UserCreateRequest;
import com.example.studyspot.auth.dto.response.GuestTokenResponse;
import com.example.studyspot.auth.service.AuthService;
import com.example.studyspot.common.api.ResponseEntityGenerator;
import com.example.studyspot.common.api.SuccessBody;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/guests/token")
    public ResponseEntity<SuccessBody<GuestTokenResponse>> generateGuestToken(
            @RequestBody UserCreateRequest userCreateRequest
    ) {
        AccessToken token = authService.generateGuestToken(userCreateRequest.ssaid());
        return ResponseEntityGenerator.success(GuestTokenResponse.from(token), HttpStatus.OK);
    }
}
