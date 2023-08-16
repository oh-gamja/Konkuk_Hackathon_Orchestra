package com.konkuk_hackathon.ohgamja.Dto.Response;

import com.konkuk_hackathon.ohgamja.Domain.Playlist;
import com.konkuk_hackathon.ohgamja.Domain.PlaylistInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class PlaylistInfoResponse {
    List<PlaylistInfo> playlistInfoList;
}
