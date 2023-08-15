package com.konkuk_hackathon.ohgamja.Dto.Response;

import com.konkuk_hackathon.ohgamja.Domain.GamePreview;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class GamesResponse {
    List<GamePreview> gamePreviews;
}
