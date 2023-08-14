package com.konkuk_hackathon.ohgamja.Service;

import com.konkuk_hackathon.ohgamja.Dao.LikeDao;
import com.konkuk_hackathon.ohgamja.Domain.LikeTopGame;
import com.konkuk_hackathon.ohgamja.Dto.Response.LikeTopResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeDao likeDao;
    public LikeTopResponse getLikeTop() {
        List<LikeTopGame> likeTopGames =  likeDao.getLikeTop();
        return new LikeTopResponse(likeTopGames);
    }
}
