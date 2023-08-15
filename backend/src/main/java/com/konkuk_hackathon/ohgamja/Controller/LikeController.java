package com.konkuk_hackathon.ohgamja.Controller;

import com.konkuk_hackathon.ohgamja.Common.Response.BaseResponse;
import com.konkuk_hackathon.ohgamja.Dto.Request.MemberGameIdRequest;
import com.konkuk_hackathon.ohgamja.Dto.Request.MemberIdRequest;
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
    public BaseResponse<GamesResponse> getLike(@RequestBody @Valid MemberIdRequest memberIdRequest) {
        log.info("[LikeController.getLike]");
        GamesResponse gamesResponse = likeService.getLike(memberIdRequest.getMemberId());
        return new BaseResponse<>(gamesResponse);
    }

    @PostMapping("/add")
    public BaseResponse<LikeResponse> addLike(@RequestBody @Valid MemberGameIdRequest memberGameIdRequest){
        log.info("[LikeController.addLike]");
        LikeResponse likeResponse = likeService.addLike(memberGameIdRequest.getMemberId(), memberGameIdRequest.getGameId());
        return new BaseResponse<>(likeResponse);
    }

    @DeleteMapping("/remove")
    public BaseResponse<LikeResponse> removeLike(@RequestBody @Valid MemberGameIdRequest memberGameIdRequest){
        log.info("[LikeController.addLike]");
        LikeResponse likeResponse = likeService.deleteLike(memberGameIdRequest.getMemberId(), memberGameIdRequest.getGameId());
        return new BaseResponse<>(likeResponse);
    }
}
