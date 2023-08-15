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
            gameDetail.setDifficulty(rs.getString("g.difficulty"));
            gameDetail.setHeadCount(rs.getInt("head_count"));
            gameDetail.setDescription(rs.getString("description"));
            gameDetail.setGameImage(rs.getString("game_image"));
            gameDetail.setLikeCount(getLikeCount(gameId));
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
        String sql = "SELECT g.game_id, g.game_name, g.category, g.difficulty, g.head_count, g.game_image, count(gl.member_id)" +
                "FROM game AS g " +
                "JOIN game_like AS gl " +
                "WHERE true";
        if(category!=null)
            sql += " AND g.category=:category";
        if(headCount!=0)
            sql += " AND g.head_count<=:headCount";
        if(name!=null)
            sql += " AND g.name LIKE '%:name'";

        RowMapper<GamePreview> mapper = (rs, rowNum) -> {
            GamePreview gamePreview = new GamePreview();
            gamePreview.setGameId(rs.getLong("g.game_id"));
            gamePreview.setGameName(rs.getString("g.game_name"));
            gamePreview.setCategory(rs.getString("g.category"));
            gamePreview.setDifficulty(rs.getString("g.difficulty"));
            gamePreview.setHeadCount(rs.getInt("g.head_count"));
            gamePreview.setGameImage(rs.getString("g.game_image"));
            gamePreview.setLikeCount(rs.getInt("count(gl.member_id)"));
            return gamePreview;
        };

        return jdbcTemplate.query(sql, mapper);
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
            gamePreview.setLikeCount(getLikeCount(rs.getLong("game_id")));
            return gamePreview;
        };

        return jdbcTemplate.query(sql, mapper);
    }
}
