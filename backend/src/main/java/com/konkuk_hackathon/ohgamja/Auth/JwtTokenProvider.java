package com.konkuk_hackathon.ohgamja.Auth;

import com.konkuk_hackathon.ohgamja.Common.Exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.JwtParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.konkuk_hackathon.ohgamja.Common.Response.Status.BaseExceptionResponseStatus.*;

@Slf4j
@Component
public class JwtTokenProvider {
    private final String secretKey;
    private final long validityInMilliseconds;
    private static final long ACCESS_TOKEN_EXPIRED_IN = 15L * 24 * 60 * 60 * 1000; // 15일

    public JwtTokenProvider(@Value("${secret.jwt-secret-key}") String secretKey,
                            @Value("${secret.jwt-expired-in}") long validityInMilliseconds) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(Long userId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRED_IN);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isExpiredToken(String token) throws InvalidTokenException {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey).build()
                    .parseClaimsJws(token);
            return claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (UnsupportedJwtException e) {
            throw new InvalidTokenException(UNSUPPORTED_TOKEN_TYPE);
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException(MALFORMED_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new InvalidTokenException(INVALID_TOKEN);
        } catch (JwtException e) {
            log.error("[JwtTokenProvider.validateAccessToken]", e);
            throw e;
        }
    }

    public String getPrincipal(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
