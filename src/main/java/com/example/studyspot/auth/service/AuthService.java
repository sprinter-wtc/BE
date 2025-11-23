package com.example.studyspot.auth.service;

import com.example.studyspot.auth.dto.AccessToken;
import com.example.studyspot.auth.jwt.JwtTokenProvider;
import com.example.studyspot.user.domain.model.User;
import com.example.studyspot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AccessToken generateGuestToken(String ssaid) {
        User user = userService.createOrFindGuestUser(ssaid);
        return new AccessToken(jwtTokenProvider.generateAccessToken(user.getId()));
    }
}
