package com.konkuk_hackathon.ohgamja.Dto.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlaylistGameIdRequest {
    private Long gameId;
    private Long playlistId;
}
