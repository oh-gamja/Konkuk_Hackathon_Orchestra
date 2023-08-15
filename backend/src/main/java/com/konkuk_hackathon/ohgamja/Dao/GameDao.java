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

@Slf4j
@Repository
public class GameDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public GameDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public GameDetail getGame(Long gameId) {
        String sql = "SELECT g.game_id, g.game_name, g.category, g.difficulty, g.head_count, g.description, g.game_image, count(gl.member_id)" +
                "FROM game AS g " +
                "JOIN game_like AS gl " +
                "WHERE g.game_id=:gameId";

        Map<String, Long> returnParam = Map.of("game_id", gameId);

        RowMapper<GameDetail> mapper = (rs, rowNum) -> {
            GameDetail gameDetail = new GameDetail();
            gameDetail.setGameId(rs.getLong("g.game_id"));
            gameDetail.setGameName(rs.getString("g.game_name"));
            gameDetail.setCategory(rs.getString("g.category"));
            gameDetail.setHeadCount(rs.getInt("g.head_count"));
            gameDetail.setDescription(rs.getString("g.description"));
            gameDetail.setGameImage(rs.getString("g.game_image"));
            gameDetail.setLikeCount(rs.getInt("count(gl.member_id)"));
            return gameDetail;
        };

        return jdbcTemplate.queryForObject(sql, returnParam, mapper);
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
            gamePreview.setHeadCount(rs.getInt("g.head_count"));
            gamePreview.setGameImage(rs.getString("g.game_image"));
            gamePreview.setLikeCount(rs.getInt("count(gl.member_id)"));
            return gamePreview;
        };

        return jdbcTemplate.query(sql, mapper);
    }


}
