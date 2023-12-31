package com.konkuk_hackathon.ohgamja.Domain;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
public class GameDetail {
    private Long gameId;
    private String gameName;
    private String category;
    private String difficulty;
    private int headCount;
    private String description;
    private String gameImage;
    private int likeCount;
}
