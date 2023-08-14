package com.konkuk_hackathon.ohgamja.Auth.Kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakao-user-client", url = "https://kauth.kakao.com")
public interface KakaoClient {
    @GetMapping("/.well-known/jwks.json")
    KakaoPublicKeys getKakaoOIDCOpenKeys();
}
