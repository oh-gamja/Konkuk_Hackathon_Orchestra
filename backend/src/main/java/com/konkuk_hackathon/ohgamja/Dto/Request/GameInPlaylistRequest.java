package com.konkuk_hackathon.ohgamja.Dto.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GameInPlaylistRequest {
    private Long playlistId;
    private Long gameId;
}
