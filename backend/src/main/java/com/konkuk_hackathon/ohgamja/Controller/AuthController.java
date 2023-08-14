package com.konkuk_hackathon.ohgamja.Controller;

import com.konkuk_hackathon.ohgamja.Common.Argument_resolver.PreAuthorize;
import com.konkuk_hackathon.ohgamja.Common.Response.BaseResponse;
import com.konkuk_hackathon.ohgamja.Dto.Request.LoginRequest;
import com.konkuk_hackathon.ohgamja.Dto.Response.LoginResponse;
import com.konkuk_hackathon.ohgamja.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // 카카오 로그인
    @PostMapping("/kakao")
    public BaseResponse<LoginResponse> kakaoLogin(@RequestBody @Valid LoginRequest loginRequest) {
        log.info("[AuthController.kakaoLogin]");
        LoginResponse loginResponse = authService.kakaoLogin(loginRequest);
        return new BaseResponse<>(loginResponse);
    }

    /**
     * 인가(JWT 검증) 테스트
     */
    @GetMapping("/test")
    public BaseResponse<String> checkAuthorization(@PreAuthorize Long userId) {
        log.info("[AuthController.checkAuthorization]");
        return new BaseResponse<>("userId = " + userId.toString());
    }
}
