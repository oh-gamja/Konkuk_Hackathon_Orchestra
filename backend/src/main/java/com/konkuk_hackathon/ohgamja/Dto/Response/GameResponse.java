package com.konkuk_hackathon.ohgamja.Dto.Response;

import lombok.*;

@Getter
@AllArgsConstructor
public class GameResponse {
    private Long gameId;
    private String gameName;
    private String category;
    private String difficulty;
    private int headCount;
    private String description;
    private String gameImage;
    private int likeCount;
}
