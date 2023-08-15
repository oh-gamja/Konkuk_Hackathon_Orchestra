package com.konkuk_hackathon.ohgamja.Domain;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
public class GamePreview {
    private Long gameId;
    private String gameName;
    private String category;
    private String difficulty;
    private int headCount;
    private String gameImage;
    private int likeCount;
}
