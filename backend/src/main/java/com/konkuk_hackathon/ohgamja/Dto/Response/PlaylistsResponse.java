package com.konkuk_hackathon.ohgamja.Dto.Response;

import com.konkuk_hackathon.ohgamja.Domain.Playlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class PlaylistsResponse {
    List<Playlist> playlistList;
}
