package com.konkuk_hackathon.ohgamja.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Playlist {
    private Long playlistId;
    private String playlistName;
    private int gameCount;
}
