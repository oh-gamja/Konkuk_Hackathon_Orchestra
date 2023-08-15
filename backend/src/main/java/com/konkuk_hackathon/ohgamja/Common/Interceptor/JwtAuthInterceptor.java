package com.konkuk_hackathon.ohgamja.Common.Interceptor;

import com.konkuk_hackathon.ohgamja.Auth.JwtTokenProvider;
import com.konkuk_hackathon.ohgamja.Common.Exception.InvalidTokenException;
import com.konkuk_hackathon.ohgamja.Dao.MemberDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.konkuk_hackathon.ohgamja.Common.Response.Status.BaseExceptionResponseStatus.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {
    private static final String JWT_TOKEN_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDao memberDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[JwtAuthInterceptor.preHandle]");
        String accessToken = resolveAccessToken(request);
        validateAccessToken(accessToken);

        String userIdStr = jwtTokenProvider.getPrincipal(accessToken);
        validatePayload(userIdStr);

        request.setAttribute("memberId", userIdStr);
        return true;
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        validateToken(token);
        System.out.println(token);
        return token.substring(JWT_TOKEN_PREFIX.length());
    }

    private void validateToken(String token) {
        if (token == null) {
            throw new InvalidTokenException(TOKEN_NOT_FOUND);
        }
        if (!token.startsWith(JWT_TOKEN_PREFIX)) {
            throw new InvalidTokenException(UNSUPPORTED_TOKEN_TYPE);
        }
    }

    private void validateAccessToken(String accessToken) {
        if (jwtTokenProvider.isExpiredToken(accessToken)) {
            throw new InvalidTokenException(EXPIRED_TOKEN);
        }
    }

    private void validatePayload(String email) {
        if (email == null) {
            throw new InvalidTokenException(INVALID_TOKEN);
        }
    }

}
