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

data class PlaylistRequest(
    @SerializedName("playlistName") val playlistName: String
)

data class DeletePlaylistRequest(
    @SerializedName("playlistId") val playlistId: Int
)



data class BaseResponse<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: T,
)

data class SignoutResponse(
    @SerializedName("memberId") val memberId: Int
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
    @SerializedName("likeTopGames") val likeTopGames: List<LikeTopGame>
)

data class  LikeTopGame(
    @SerializedName("gameId") val gameId: Int,
    @SerializedName("name") val gameName: String,
    @SerializedName("gameImage") val gameImage: String,
    @SerializedName("likeCount") val likeCount: Int,
)

data class  PlaylistResponse(
    @SerializedName("playlistList") val playlistList: List<Playlist>,
)

data class  Playlist(
    @SerializedName("playlistId") val playlistId: Int,
    @SerializedName("playlistName") val playlistName: String,
    @SerializedName("gameCount") val gameCount: Int,
)

data class DetailResponse(
    @SerializedName("gameId") val gameId: Int,
    @SerializedName("gameName") val gameName: String,
    @SerializedName("category") val category: String,
    @SerializedName("difficulty") val difficulty: String,
    @SerializedName("headCount") val headCount: Int,
    @SerializedName("description") val description: String,
    @SerializedName("gameImage") val gameImage: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("isLike") val isLike: Boolean
)

data class gameIdRequest(
    @SerializedName("gameId") val gameId: Int
)

data class resultResponse(
    @SerializedName("result") val result: String
)

data class  SearchResponse(
    @SerializedName("gamePreviews") val gamePreviews: List<Search>,
)

data class  Search(
    @SerializedName("gameId") val gameId: Int,
    @SerializedName("gameName") val gameName: String,
    @SerializedName("category") val category: String,
    @SerializedName("difficulty") val difficulty: String,
    @SerializedName("headCount") val headCount: Int,
    @SerializedName("gameImage") val gameImage: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("like") val like: Boolean
)

data class playlistInfo(
    @SerializedName("playlistId") val playlistId: Int,
    @SerializedName("playlistName") val playlistName: String,
    @SerializedName("gameCount") val gameCount: Int,
    @SerializedName("isInPlaylist") val isInPlaylist: Boolean
)

data class playlistInfoList(
    @SerializedName("playlistInfoList") val playlistInfoList: List<playlistInfo>
)

data class deleteListRequest(
    @SerializedName("playlistId") val playlistId: Int,
    @SerializedName("gameId") val gameId: Int
)




