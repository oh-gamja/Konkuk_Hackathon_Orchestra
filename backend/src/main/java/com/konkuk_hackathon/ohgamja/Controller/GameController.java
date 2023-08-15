package com.konkuk_hackathon.ohgamja.Controller;

import com.konkuk_hackathon.ohgamja.Common.Response.BaseResponse;
import com.konkuk_hackathon.ohgamja.Dto.Request.MemberGameIdRequest;
import com.konkuk_hackathon.ohgamja.Dto.Response.GameResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.GamesResponse;
import com.konkuk_hackathon.ohgamja.Service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @GetMapping("")
    public BaseResponse<GamesResponse> getGamePreviewsBy(@RequestParam String category, @RequestParam String name, @RequestParam int headCount, @RequestParam Long memberId) {
        GamesResponse gamesResponse = gameService.getGamePreviews(category, name, headCount, memberId);
        return new BaseResponse<>(gamesResponse);
    }

    @GetMapping("/detail")
    public BaseResponse<GameResponse> getGame(@RequestBody @Valid MemberGameIdRequest memberGameIdRequest){
        GameResponse gameResponse = gameService.getGame(memberGameIdRequest.getGameId(), memberGameIdRequest.getMemberId());
        return new BaseResponse<>(gameResponse);
    }

    @GetMapping("/all")
    public BaseResponse<GamesResponse> getAllGamePreViews(@RequestBody @Valid MemberGameIdRequest memberGameIdRequest) {
        GamesResponse gamesResponse = gameService.getAllGamePreviews(memberGameIdRequest.getMemberId());
        return new BaseResponse<>(gamesResponse);
    }
}
