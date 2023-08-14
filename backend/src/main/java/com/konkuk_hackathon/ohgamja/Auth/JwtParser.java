package com.konkuk_hackathon.ohgamja.Auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk_hackathon.ohgamja.Common.Exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

import static com.konkuk_hackathon.ohgamja.Common.Response.Status.BaseExceptionResponseStatus.*;

@Component
public class JwtParser {
    private static final String IDENTITY_TOKEN_SPLITER = "\\.";
    private static final int HEADER_INDEX = 0;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, String> parseHeaders(String authorizationCode) {
        try {
            String encodeHeader = authorizationCode.split(IDENTITY_TOKEN_SPLITER)[HEADER_INDEX];
            String decodeHeader = new String(Base64.getDecoder().decode(encodeHeader));
            return objectMapper.readValue(decodeHeader, Map.class);
        } catch (JsonProcessingException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidTokenException(UNSUPPORTED_AUTHORIZATIONCODE_TYPE, e.getMessage());
        }
    }

    public Claims getClaimsFromIdTokenWithPublicKey(String authorizationCode, PublicKey publicKey) {
        try {
            return Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(authorizationCode)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException(EXPIRED_CODE);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
            throw new InvalidTokenException(MALFORMED_CODE, e.getMessage());
        }
    }
}
