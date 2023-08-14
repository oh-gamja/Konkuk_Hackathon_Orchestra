package com.konkuk_hackathon.ohgamja.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeTopGame {
    private long gameId;
    private String name;
    private String gameImage;
    private int likeCount;
}
