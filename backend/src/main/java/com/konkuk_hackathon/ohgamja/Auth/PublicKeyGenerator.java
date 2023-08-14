package com.konkuk_hackathon.ohgamja.Auth;

import com.konkuk_hackathon.ohgamja.Auth.Kakao.KakaoPublicKey;
import com.konkuk_hackathon.ohgamja.Auth.Kakao.KakaoPublicKeys;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

@Component
public class PublicKeyGenerator {
    private static final String HEADER_SIGN_ALGORITHM = "alg";
    private static final String HEADER_KEY_ID = "kid";
    private static final int POSITIVE_SIGN_NUMBER = 1;

    public PublicKey generatePublicKey(Map<String, String> headers, KakaoPublicKeys kakaoPublicKeys) {
        KakaoPublicKey kakaoPublicKey = kakaoPublicKeys.getMatchedKey(headers.get(HEADER_KEY_ID));
        return generatePublicKeyWithKakaoPublicKey(kakaoPublicKey);
    }

    private PublicKey generatePublicKeyWithKakaoPublicKey(KakaoPublicKey kakaoPublicKey) {
        byte[] nBytes = Base64.getUrlDecoder().decode(kakaoPublicKey.getN());
        byte[] eBytes = Base64.getUrlDecoder().decode(kakaoPublicKey.getE());

        BigInteger n = new BigInteger(POSITIVE_SIGN_NUMBER, nBytes);
        BigInteger e = new BigInteger(POSITIVE_SIGN_NUMBER, eBytes);

        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(kakaoPublicKey.getKty());
            return keyFactory.generatePublic(publicKeySpec);
        }catch (NoSuchAlgorithmException | InvalidKeySpecException exception){
            throw new IllegalStateException("OAuth 로그인 중 public key 생성에 문제가 발생했습니다.");
        }
    }
}
