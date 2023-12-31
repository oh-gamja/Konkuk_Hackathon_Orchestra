package com.konkuk_hackathon.ohgamja.Dao;

import com.konkuk_hackathon.ohgamja.Domain.GameLike;
import com.konkuk_hackathon.ohgamja.Domain.GamePreview;
import com.konkuk_hackathon.ohgamja.Domain.LikeTopGame;
import com.konkuk_hackathon.ohgamja.Domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class LikeDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LikeDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<LikeTopGame> getLikeTop() {
        String sql = "select g.game_id, g.game_name, g.game_image, count(gl.member_id) " +
                "from game as g join game_like as gl on g.game_id=gl.game_id " +
                "group by gl.game_id order by count(gl.member_id) desc LIMIT 3";

        RowMapper<LikeTopGame> mapper = (rs, rowNum) -> {
            LikeTopGame likeTopGame = new LikeTopGame();
            likeTopGame.setGameId(rs.getLong("g.game_id"));
            likeTopGame.setName(rs.getString("g.game_name"));
            likeTopGame.setGameImage(rs.getString("g.game_image"));
            likeTopGame.setLikeCount(rs.getInt("count(gl.member_id)"));
            return likeTopGame;
        };

        return jdbcTemplate.query(sql, mapper);
    }

    public List<GamePreview> getLike(Long memberId) {
        String sql = "select g.game_id, g.game_name, g.category, g.difficulty, g.head_count, g.game_image, count(gl.member_id) " +
                "from game as g join game_like as gl on g.game_id=gl.game_id " +
                "where gl.member_id=:member_id group by gl.game_id order by count(gl.member_id) desc";
        Map<String, Long> param = Map.of("member_id", memberId);

        RowMapper<GamePreview> mapper = (rs, rowNum) -> {
            GamePreview gamePreview = new GamePreview();
            gamePreview.setGameId(rs.getLong("g.game_id"));
            gamePreview.setGameName(rs.getString("g.game_name"));
            gamePreview.setCategory(rs.getString("g.category"));
            gamePreview.setHeadCount(rs.getInt("g.head_count"));
            gamePreview.setDifficulty(rs.getString("g.difficulty"));
            gamePreview.setGameImage(rs.getString("g.game_image"));
            gamePreview.setLikeCount(rs.getInt("count(gl.member_id)"));
            return gamePreview;
        };

        return jdbcTemplate.query(sql, param, mapper);
    }

    public void delete(GameLike gameLike) {
        String sql = "delete from game_like " +
                "where member_id=:member_id " +
                "and game_id=:game_id";
        Map<String, Long> param = Map.of("member_id", gameLike.getMemberId(),
                "game_id", gameLike.getGameId());

        jdbcTemplate.update(sql, param);
    }

    public void save(GameLike gameLike) {
        // duplicate check 필요할까?
        String sql = "insert into game_like (member_id, game_id) values (:member_id, :game_id)";
        Map<String, Long> param = Map.of("member_id", gameLike.getMemberId(),
                "game_id", gameLike.getGameId());

        jdbcTemplate.update(sql, param);
    }

}
