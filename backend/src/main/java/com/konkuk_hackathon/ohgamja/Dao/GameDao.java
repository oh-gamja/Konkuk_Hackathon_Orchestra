package com.konkuk_hackathon.ohgamja.Dao;

import com.konkuk_hackathon.ohgamja.Domain.Game;
import com.konkuk_hackathon.ohgamja.Domain.GameDetail;
import com.konkuk_hackathon.ohgamja.Domain.GamePreview;
import com.konkuk_hackathon.ohgamja.Domain.LikeTopGame;
import com.konkuk_hackathon.ohgamja.Dto.Response.GameResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class GameDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public GameDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public GameDetail getGame(Long gameId) {
        String sql = "select * from game where game_id=:game_id";

        Map<String, Object> returnParam = Map.of("game_id", gameId);

        RowMapper<GameDetail> mapper = (rs, rowNum) -> {
            GameDetail gameDetail = new GameDetail();
            gameDetail.setGameId(rs.getLong("game_id"));
            gameDetail.setGameName(rs.getString("game_name"));
            gameDetail.setCategory(rs.getString("category"));
            gameDetail.setDifficulty(rs.getString("difficulty"));
            gameDetail.setHeadCount(rs.getInt("head_count"));
            gameDetail.setDescription(rs.getString("description"));
            gameDetail.setGameImage(rs.getString("game_image"));
            return gameDetail;
        };

        return jdbcTemplate.queryForObject(sql, returnParam, mapper);
    }

    public Integer getLikeCount(Long gameId) {
        String sql = "select count(member_id) " +
                "from game_like " +
                "where game_id=:game_id";
        Map<String, Object> param = Map.of("game_id", gameId);

        return jdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    public List<GamePreview> getGamePreviews(String category, String name, int headCount) {
        System.out.println("category : " + category + " name : " + name + " headCount : " + headCount);

        String sql = "select game_id, game_name, category, difficulty, head_count, game_image " +
                "from game WHERE category=:category AND head_count<=:head_count AND game_name LIKE :game_name";

        Map<String, Object> param = Map.of("category", category,
                "head_count", headCount,
                    "game_name", "%"+name+"%");


        RowMapper<GamePreview> mapper = (rs, rowNum) -> {
            GamePreview gamePreview = new GamePreview();
            gamePreview.setGameId(rs.getLong("game_id"));
            gamePreview.setGameName(rs.getString("game_name"));
            gamePreview.setCategory(rs.getString("category"));
            gamePreview.setDifficulty(rs.getString("difficulty"));
            gamePreview.setHeadCount(rs.getInt("head_count"));
            gamePreview.setGameImage(rs.getString("game_image"));
            return gamePreview;
        };

        return jdbcTemplate.query(sql, param, mapper);
    }

    public Boolean getIsLike(Long gameId, Long memberId) {
        String sql = "select exists(select * from game_like where game_id=:game_id and member_id=:member_id);";
        Map<String, Object> param = Map.of("game_id", gameId,
                "member_id", memberId);

        return jdbcTemplate.queryForObject(sql, param, Boolean.class);
    }

    public List<GamePreview> getAllGamePreviews() {
        String sql = "select * from game";

        RowMapper<GamePreview> mapper = (rs, rowNum) -> {
            GamePreview gamePreview = new GamePreview();
            gamePreview.setGameId(rs.getLong("game_id"));
            gamePreview.setGameName(rs.getString("game_name"));
            gamePreview.setCategory(rs.getString("category"));
            gamePreview.setHeadCount(rs.getInt("head_count"));
            gamePreview.setDifficulty(rs.getString("difficulty"));
            gamePreview.setGameImage(rs.getString("game_image"));
            return gamePreview;
        };

        return jdbcTemplate.query(sql, mapper);
    }

    public Integer getGamecount() {
        String sql = "select count(game_id) " +
                "from game";

        Map<String, Object> param = Map.of();
        return jdbcTemplate.queryForObject(sql, param, Integer.class);
    }
}
