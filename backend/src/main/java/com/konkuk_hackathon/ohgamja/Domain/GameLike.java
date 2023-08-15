package com.konkuk_hackathon.ohgamja.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class GameLike {
    private Long gameLikeId;
    private Long memberId;
    private Long gameId;

    public GameLike(Long memberId, Long gameId){
        this.memberId = memberId;
        this.gameId = gameId;
    }
}
