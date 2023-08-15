package com.konkuk_hackathon.ohgamja.Dao;

import com.konkuk_hackathon.ohgamja.Domain.GamePreview;
import com.konkuk_hackathon.ohgamja.Domain.LikeTopGame;
import com.konkuk_hackathon.ohgamja.Domain.Playlist;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class PlaylistDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PlaylistDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void createPlaylist(String playlistName, Long memberId) {
        String sql = "insert into playlist (playlist_name, member_id) values (:playlist_name, :member_id)";
        Map<String, Object> param = Map.of("playlist_name", playlistName,
                "member_id", memberId);

        jdbcTemplate.update(sql, param);
    }

    public List<Playlist> getPlaylist(Long memberId) {
        String sql = "select * from playlist where member_id=:member_id";
        Map<String, Object> param = Map.of("member_id", memberId);

        RowMapper<Playlist> mapper = (rs, rowNum) -> {
            Playlist playlist = new Playlist();
            playlist.setPlaylistId(rs.getLong("playlist_id"));
            playlist.setPlaylistName(rs.getString("playlist_name"));
            playlist.setGameCount(getGameInPlaylist(rs.getLong("playlist_id")));
            return playlist;
        };

        return jdbcTemplate.query(sql, param, mapper);
    }

    public Integer getGameInPlaylist(Long playlistId) {
        String sql = "select count(game_id) from playlist_detail where playlist_id=:playlist_id";
        Map<String, Object> param = Map.of("playlist_id", playlistId);

        return jdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    public void deletePlaylist(Long playlistId) {
        String playlistSql = "delete from playlist " +
                "where playlist_id=:playlist_id";

        String playlistDetailSql = "delete from playlist_detail " +
                "where playlist_id=:playlist_id";

        Map<String, Long> param = Map.of("playlist_id", playlistId);
        jdbcTemplate.update(playlistDetailSql, param);
        jdbcTemplate.update(playlistSql, param);
    }

    public List<GamePreview> getPlaylistDetail(Long playlistId, Long memberId) {
        String sql = "select g.game_id, g.game_name, g.category, g.difficulty, g.head_count, g.game_image " +
                "from playlist_detail as pd join game as g on pd.game_id=g.game_id " +
                "where playlist_id=:playlist_id";
        Map<String, Long> param = Map.of("playlist_id", playlistId);

        RowMapper<GamePreview> mapper = (rs, rowNum) -> {
            GamePreview gamePreview = new GamePreview();
            gamePreview.setGameId(rs.getLong("g.game_id"));
            gamePreview.setGameName(rs.getString("g.game_name"));
            gamePreview.setCategory(rs.getString("g.category"));
            gamePreview.setDifficulty(rs.getString("g.difficulty"));
            gamePreview.setHeadCount(rs.getInt("g.head_count"));
            gamePreview.setGameImage(rs.getString("g.game_image"));
            return gamePreview;
        };

        return jdbcTemplate.query(sql, param, mapper);
    }
}
