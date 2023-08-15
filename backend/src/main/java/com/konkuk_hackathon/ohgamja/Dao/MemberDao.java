package com.konkuk_hackathon.ohgamja.Dao;

import com.konkuk_hackathon.ohgamja.Domain.Member;
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
public class MemberDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Long> findIdByPlatformId(String platformId) {
        String sql = "select member_id from member where platform_id=:platform_id and status=1";
        Map<String, String> param = Map.of("platform_id", platformId);

        RowMapper<Long> mapper = new SingleColumnRowMapper<>(Long.class);

        return jdbcTemplate.query(sql, param, mapper);
    }

    public Member findUserById(Long memberId) {
        String sql = "select * from member where member_id=:member_id and status=1";
        Map<String, Long> param = Map.of("member_id", memberId);

        RowMapper<Member> mapper = (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getLong("member_id"));
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            member.setMemberImgUrl(rs.getString("img_url"));
            member.setPlatformId(rs.getString("platform_id"));
            return member;
        };

        return jdbcTemplate.queryForObject(sql, param, mapper);
    }

    public Member save(Member oauthMember) {
        // 회원가입 -> insert 한 후, 넣은 애 반환
        String sql = "insert into member (name, email, platform_id, img_url) values (:name, :email, :platform_id, :img_url)";
        Map<String, String> param = Map.of("name", oauthMember.getName(),
                "email", oauthMember.getEmail(),
                "platform_id", oauthMember.getPlatformId(),
                "img_url", oauthMember.getMemberImgUrl());

        jdbcTemplate.update(sql, param);

        String returnSql = "select * from member where platform_id=:platform_id";
        Map<String, String> returnParam = Map.of("platform_id", oauthMember.getPlatformId());

        RowMapper<Member> returnMapper = (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getLong("member_id"));
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            member.setMemberImgUrl(rs.getString("img_url"));
            member.setPlatformId(rs.getString("platform_id"));
            return member;
        };

        return jdbcTemplate.queryForObject(returnSql, returnParam, returnMapper);
    }

    public Long findIdByEmail(String email) {
        String sql = "select member_id from member where email=:email and status=1";
        Map<String, Object> param = Map.of("email", email);
        return jdbcTemplate.queryForObject(sql, param, long.class);
    }

    public int delete(Long memberId) {
        String sql = "delete from member where member_id=:memberId";
        Map<String, Long> param = Map.of("member_id", memberId);

        return jdbcTemplate.update(sql, param);
    }

}
