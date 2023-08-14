package com.konkuk_hackathon.ohgamja.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoPlatformUserResponse {
    private String name;
    private String email;
    private String platformId;
    private String image;
}
