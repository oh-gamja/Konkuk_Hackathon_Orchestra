package com.example.ohgamja_frontend.ui.retrofit

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("email") val email: String,
    @SerializedName("isRegistered") val isRegistered: Boolean
)

data class LoginRequest(
    @SerializedName("idToken") val idToken: String
)

data class BaseResponse<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: T,
)

data class GamesResponse(
    @SerializedName("gamePreviews") val gamePreviews: List<GamePreview>
)

data class GamePreview(
    @SerializedName("gameId") val gameId: Int,
    @SerializedName("gameName") val gameName: String,
    @SerializedName("category") val category: String,
    @SerializedName("difficulty") val difficulty: String,
    @SerializedName("headCount") val headCount: Int,
    @SerializedName("gameImage") val gameImage: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("like") val like: Boolean
)

data class  TopGameResponse(
    @SerializedName("likeTopGames") val likeTopGames: List<LikeTopGame>,
    )

data class  LikeTopGame(
    @SerializedName("gameId") val gameId: Int,
    @SerializedName("name") val gameName: String,
    @SerializedName("gameImage") val gameImage: String,
    @SerializedName("likeCount") val likeCount: Int,
)

data class  PlaylistResponse(
    @SerializedName("playListResponse") val playListResponse: List<Playlist>,
)

data class  Playlist(
    @SerializedName("playlistId") val playlistId: Int,
    @SerializedName("playlistName") val playlistName: String,
    @SerializedName("gameCount") val gameCount: Int,
)