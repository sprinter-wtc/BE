package com.example.studyspot.common.annotation.resolver;

import com.example.studyspot.auth.exception.AuthErrorType;
import com.example.studyspot.auth.exception.AuthException;
import com.example.studyspot.auth.jwt.JwtTokenProvider;
import com.example.studyspot.common.annotation.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserIdResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        String header = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) throw new AuthException(AuthErrorType.NOT_EXIST_TOKEN);

        String token = jwtTokenProvider.extractToken(header);
        return jwtTokenProvider.getUserIFromToken(token);
    }
}