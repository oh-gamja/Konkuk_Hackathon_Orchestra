package com.konkuk_hackathon.ohgamja.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Game {
    private Long gameId;
    private String gameName;
    private String category;
    private String difficulty;
    private int headCount;
    private String description;
    private String gameImage;
}
