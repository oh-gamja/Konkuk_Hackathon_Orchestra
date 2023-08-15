package com.konkuk_hackathon.ohgamja.Service;

import com.konkuk_hackathon.ohgamja.Dao.GameDao;
import com.konkuk_hackathon.ohgamja.Domain.GameDetail;
import com.konkuk_hackathon.ohgamja.Domain.GamePreview;
import com.konkuk_hackathon.ohgamja.Dto.Response.GameResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.GamesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {
    private final GameDao gameDao;

    public GameResponse getGame(Long gameId) {
        GameDetail gameDetail = gameDao.getGame(gameId);
        return new GameResponse(gameDetail.getGameId(), gameDetail.getGameName(), gameDetail.getCategory(), gameDetail.getDifficulty(), gameDetail.getHeadCount(), gameDetail.getDescription(), gameDetail.getGameImage(), gameDetail.getLikeCount());
    }

    public GamesResponse getGamePreviews(String category, String name, int headCount){
        List<GamePreview> gamePreviews = gameDao.getGamePreviews(category, name, headCount);
        return new GamesResponse(gamePreviews);
    }
}



