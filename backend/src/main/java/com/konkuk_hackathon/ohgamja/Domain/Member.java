package com.konkuk_hackathon.ohgamja.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long memberId;
    private String name;
    private String email;
    private String memberImgUrl;
    private String platformId;

    public Member(String name, String email, String platformId, String memberImgUrl) {
        this.name = name;
        this.email = email;
        this.platformId = platformId;
        this.memberImgUrl = memberImgUrl;
    }
}
