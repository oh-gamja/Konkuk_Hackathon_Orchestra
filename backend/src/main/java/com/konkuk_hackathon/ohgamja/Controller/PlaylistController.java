package com.konkuk_hackathon.ohgamja.Controller;

import com.konkuk_hackathon.ohgamja.Common.Argument_resolver.PreAuthorize;
import com.konkuk_hackathon.ohgamja.Common.Response.BaseResponse;
import com.konkuk_hackathon.ohgamja.Dto.Request.PlaylistIdRequest;
import com.konkuk_hackathon.ohgamja.Dto.Request.PlaylistRequest;
import com.konkuk_hackathon.ohgamja.Dto.Response.PlaylistsResponse;
import com.konkuk_hackathon.ohgamja.Service.PlaylistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    public BaseResponse<String> createPlaylist(@RequestBody @Valid PlaylistRequest playlistRequest, @PreAuthorize Long memberId) {
        String response = playlistService.createPlaylist(playlistRequest.getPlaylistName(), memberId);
        return new BaseResponse<>(response);
    }

    @GetMapping
    public BaseResponse<PlaylistsResponse> getPlaylist(@PreAuthorize Long memberId) {
        PlaylistsResponse playlistsResponse = playlistService.getPlaylist(memberId);
        return new BaseResponse<>(playlistsResponse);
    }

    @DeleteMapping("/remove")
    public BaseResponse<String> deletePlaylist(@RequestBody @Valid PlaylistIdRequest playlistIdRequest) {
        String response = playlistService.daletePlaylist(playlistIdRequest.getPlaylistId());
        return new BaseResponse<>(response);
    }

}
