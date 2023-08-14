package com.konkuk_hackathon.ohgamja.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long userId;
    private String name;
    private String email;
    private String userImgUrl;
    private String platformId;

    public User(String name, String email, String platformId, String userImgUrl) {
        this.name = name;
        this.email = email;
        this.platformId = platformId;
        this.userImgUrl = userImgUrl;
    }
}
