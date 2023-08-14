package com.konkuk_hackathon.ohgamja.Dao;

import com.konkuk_hackathon.ohgamja.Domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Long> findIdByPlatformId(String platformId) {
        String sql = "select member_id from member where platform_id=:platform_id and status=1";
        Map<String, String> param = Map.of("platform_id", platformId);

        RowMapper<Long> mapper = new SingleColumnRowMapper<>(Long.class);

        return jdbcTemplate.query(sql, param, mapper);
    }

    public User findUserById(Long userId) {
        String sql = "select * from member where member_id=:member_id and status=1";
        Map<String, Long> param = Map.of("member_id", userId);

        RowMapper<User> mapper = (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getLong("member_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setUserImgUrl(rs.getString("img_url"));
            user.setPlatformId(rs.getString("platform_id"));
            return user;
        };

        return jdbcTemplate.queryForObject(sql, param, mapper);
    }

    public User save(User oauthUser) {
        // 회원가입 -> insert 한 후, 넣은 애 반환
        String sql = "insert into member (name, email, platform_id, img_url) values (:name, :email, :platform_id, :img_url)";
        Map<String, String> param = Map.of("name", oauthUser.getName(),
                "email", oauthUser.getEmail(),
                "platform_id", oauthUser.getPlatformId(),
                "img_url", oauthUser.getUserImgUrl());

        jdbcTemplate.update(sql, param);

        String returnSql = "select * from member where platform_id=:platform_id";
        Map<String, String> returnParam = Map.of("platform_id", oauthUser.getPlatformId());

        RowMapper<User> returnMapper = (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getLong("member_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setUserImgUrl(rs.getString("img_url"));
            user.setPlatformId(rs.getString("platform_id"));
            return user;
        };

        return jdbcTemplate.queryForObject(returnSql, returnParam, returnMapper);
    }

    public Long findIdByEmail(String email) {
        String sql = "select member_id from member where email=:email and status=1";
        Map<String, Object> param = Map.of("email", email);
        return jdbcTemplate.queryForObject(sql, param, long.class);
    }

}
