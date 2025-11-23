package com.example.studyspot.auth.dto.response;

import com.example.studyspot.auth.dto.AccessToken;

public record GuestTokenResponse (
        String accessToken
){
    public static GuestTokenResponse from(AccessToken accessToken) {
        return new GuestTokenResponse(accessToken.accessToken());
    }
}
