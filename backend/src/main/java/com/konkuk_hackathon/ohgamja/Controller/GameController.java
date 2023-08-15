package com.konkuk_hackathon.ohgamja.Controller;

import com.konkuk_hackathon.ohgamja.Common.Response.BaseResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.GameResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.GamesResponse;
import com.konkuk_hackathon.ohgamja.Service.GameService;
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
    public BaseResponse<GamesResponse> getGamePreviewsBy(@RequestParam String category, @RequestParam String name, @RequestParam int headCount) {
        GamesResponse gamesResponse = gameService.getGamePreviews(category, name, headCount);
        return new BaseResponse<>(gamesResponse);
    }

    @GetMapping("/:id")
    public BaseResponse<GameResponse> getGame(@RequestBody Long id){
        GameResponse gameResponse = gameService.getGame(id);
        return new BaseResponse<>(gameResponse);
    }
}
