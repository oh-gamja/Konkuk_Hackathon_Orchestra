package com.konkuk_hackathon.ohgamja.Dto.Response;

import com.konkuk_hackathon.ohgamja.Domain.LikeTopGame;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LikeTopResponse {
    private List<LikeTopGame> likeTopGames;
}
