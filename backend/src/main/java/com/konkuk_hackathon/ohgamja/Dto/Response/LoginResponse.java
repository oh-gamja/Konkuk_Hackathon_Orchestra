package com.konkuk_hackathon.ohgamja.Dto.Response;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class LoginResponse {
    private String accessToken;
    private String email;
    private Boolean isRegistered;
}
