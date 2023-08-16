package com.konkuk_hackathon.ohgamja.Controller;

import com.konkuk_hackathon.ohgamja.Common.Argument_resolver.PreAuthorize;
import com.konkuk_hackathon.ohgamja.Common.Response.BaseResponse;
import com.konkuk_hackathon.ohgamja.Dto.Request.GameIdRequest;
import com.konkuk_hackathon.ohgamja.Dto.Response.GamesResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.LikeResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.LikeTopResponse;
import com.konkuk_hackathon.ohgamja.Service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/top")
    public BaseResponse<LikeTopResponse> getLikeTop() {
        log.info("[LikeController.getLikeTop]");
        LikeTopResponse likeTopReaponse = likeService.getLikeTop();
        return new BaseResponse<>(likeTopReaponse);
    }

    @GetMapping
    public BaseResponse<GamesResponse> getLike(@PreAuthorize Long memberId) {
        log.info("[LikeController.getLike]");
        GamesResponse gamesResponse = likeService.getLike(memberId);
        return new BaseResponse<>(gamesResponse);
    }

    @PostMapping("/add")
    public BaseResponse<LikeResponse> addLike(@PreAuthorize Long memberId, @ModelAttribute @Valid GameIdRequest gameIdRequest){
        log.info("[LikeController.addLike]");
        LikeResponse likeResponse = likeService.addLike(memberId,gameIdRequest.getGameId());
        return new BaseResponse<>(likeResponse);
    }

    @DeleteMapping("/remove")
    public BaseResponse<LikeResponse> removeLike(@PreAuthorize Long memberId, @ModelAttribute @Valid GameIdRequest gameIdRequest){
        log.info("[LikeController.addLike]");
        LikeResponse likeResponse = likeService.deleteLike(memberId,gameIdRequest.getGameId());
        return new BaseResponse<>(likeResponse);
    }
}
