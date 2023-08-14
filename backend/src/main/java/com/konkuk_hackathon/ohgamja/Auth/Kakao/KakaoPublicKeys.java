package com.konkuk_hackathon.ohgamja.Auth.Kakao;

import com.konkuk_hackathon.ohgamja.Auth.Kakao.KakaoPublicKey;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoPublicKeys {
    private List<KakaoPublicKey> keys;

    public KakaoPublicKey getMatchedKey(String kid) {
        // 카카오로부터 받은 공개키들 중에 일치히는 공개키 하나 찾기
        return this.keys.stream()
                .filter(o -> o.getKid().equals(kid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Kakao JWT 값의 kid 정보가 올바르지 않습니다."));
    }
}
