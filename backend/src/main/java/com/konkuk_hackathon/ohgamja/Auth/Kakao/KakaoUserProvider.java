package com.konkuk_hackathon.ohgamja.Auth.Kakao;

import com.konkuk_hackathon.ohgamja.Auth.JwtParser;
import com.konkuk_hackathon.ohgamja.Auth.Kakao.KakaoClient;
import com.konkuk_hackathon.ohgamja.Auth.Kakao.KakaoPublicKeys;
import com.konkuk_hackathon.ohgamja.Auth.PublicKeyGenerator;
import com.konkuk_hackathon.ohgamja.Common.Exception.InvalidTokenException;
import com.konkuk_hackathon.ohgamja.Dto.Response.KakaoPlatformUserResponse;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Map;

import static com.konkuk_hackathon.ohgamja.Common.Response.Status.BaseExceptionResponseStatus.INVALID_CLAIMS;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoUserProvider {
    private final KakaoClient kakaoClient;
    @Value("${oauth.kakao.iss}")
    private String iss;
    @Value("${oauth.kakao.client-id}")
    private String kakaoClientId;

    private final JwtParser jwtParser;
    private final PublicKeyGenerator publicKeyGenerator;
    public KakaoPlatformUserResponse getKakaoPlatformUser(String authorizationCode) {
        Map<String, String> headers = jwtParser.parseHeaders(authorizationCode);
        KakaoPublicKeys kakaoPublicKeys = kakaoClient.getKakaoOIDCOpenKeys();
        PublicKey publicKey = publicKeyGenerator.generatePublicKey(headers, kakaoPublicKeys);

        Claims claims = jwtParser.getClaimsFromIdTokenWithPublicKey(authorizationCode, publicKey);
        validateClaims(claims);

        log.info(claims.get("nickname", String.class));
        log.info(claims.get("picture", String.class));
        return new KakaoPlatformUserResponse(claims.get("nickname", String.class),
                claims.get("email", String.class),
                claims.getSubject(),
                claims.get("picture", String.class));
    }

    private void validateClaims(Claims claims) {
        if (!claims.getIssuer().contains(iss) && claims.getAudience().equals(kakaoClientId)) {
            throw new InvalidTokenException(INVALID_CLAIMS);
        }
    }
}
