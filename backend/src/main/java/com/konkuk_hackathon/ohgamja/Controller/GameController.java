package com.konkuk_hackathon.ohgamja.Controller;

import com.konkuk_hackathon.ohgamja.Common.Argument_resolver.PreAuthorize;
import com.konkuk_hackathon.ohgamja.Common.Response.BaseResponse;
import com.konkuk_hackathon.ohgamja.Dto.Request.GameIdRequest;
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
    public BaseResponse<GamesResponse> getGamePreviewsBy(@RequestParam String difficulty, @RequestParam String category, @RequestParam String name, @RequestParam int headCount, @PreAuthorize Long memberId) {
        GamesResponse gamesResponse = gameService.getGamePreviews(difficulty, category, name, headCount, memberId);
        return new BaseResponse<>(gamesResponse);
    }

    @GetMapping("/detail")
    public BaseResponse<GameResponse> getGame(@ModelAttribute @Valid GameIdRequest gameIdRequest, @PreAuthorize Long memberId){
        GameResponse gameResponse = gameService.getGame(gameIdRequest.getGameId(), memberId);
        return new BaseResponse<>(gameResponse);
    }

    @GetMapping("/all")
    public BaseResponse<GamesResponse> getAllGamePreViews(@PreAuthorize Long memberId) {
        GamesResponse gamesResponse = gameService.getAllGamePreviews(memberId);
        return new BaseResponse<>(gamesResponse);
    }

    @GetMapping("/random")
    public BaseResponse<GameResponse> getRandomGame(@PreAuthorize Long memberId) {
        GameResponse gameResponse = gameService.getRandomGame(memberId);
        return new BaseResponse<>(gameResponse);
    }
}
