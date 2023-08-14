package com.konkuk_hackathon.ohgamja.Dao;

import com.konkuk_hackathon.ohgamja.Domain.LikeTopGame;
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


}
