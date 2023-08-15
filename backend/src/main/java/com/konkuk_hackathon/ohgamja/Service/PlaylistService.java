package com.konkuk_hackathon.ohgamja.Service;

import com.konkuk_hackathon.ohgamja.Dao.PlaylistDao;
import com.konkuk_hackathon.ohgamja.Domain.Playlist;
import com.konkuk_hackathon.ohgamja.Dto.Request.PlaylistIdRequest;
import com.konkuk_hackathon.ohgamja.Dto.Response.PlaylistsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistDao playlistDao;

    public String createPlaylist(String playlistName, Long memberId) {
        playlistDao.createPlaylist(playlistName, memberId);
        return "good";
    }

    public PlaylistsResponse getPlaylist(Long memberId) {
        List<Playlist> playlistsResponse = playlistDao.getPlaylist(memberId);
        return new PlaylistsResponse(playlistsResponse);
    }

    public String daletePlaylist(Long playlistId) {
        playlistDao.deletePlaylist(playlistId);
        return "good";
    }
}
