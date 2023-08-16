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
    @SerializedName("gamePreviews") val gamePreviews: List<GamePreview>,
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

data class DetailRequest(
    @SerializedName("gameId") val gameId: Int
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

