package com.konkuk_hackathon.ohgamja.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GameInPlaylistRequest {
    private Long playlistId;
    private Long gameId;
}