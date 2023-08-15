package com.konkuk_hackathon.ohgamja.Controller;

import com.konkuk_hackathon.ohgamja.Common.Response.BaseResponse;
import com.konkuk_hackathon.ohgamja.Dto.Request.LikeRequest;
import com.konkuk_hackathon.ohgamja.Dto.Request.LoginRequest;
import com.konkuk_hackathon.ohgamja.Dto.Response.LikeResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.LikeTopResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.LoginResponse;
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

    @PostMapping("/add")
    public BaseResponse<LikeResponse> addLike(@RequestBody @Valid LikeRequest likeRequest){
        log.info("[LikeController.addLike]");
        LikeResponse likeResponse = likeService.addLike(likeRequest.getMemberId(), likeRequest.getGameId());
        return new BaseResponse<>(likeResponse);
    }

    @DeleteMapping("/remove")
    public BaseResponse<LikeResponse> removeLike(@RequestBody @Valid LikeRequest likeRequest){
        log.info("[LikeController.addLike]");
        LikeResponse likeResponse = likeService.deleteLike(likeRequest.getMemberId(), likeRequest.getGameId());
        return new BaseResponse<>(likeResponse);
    }
}
