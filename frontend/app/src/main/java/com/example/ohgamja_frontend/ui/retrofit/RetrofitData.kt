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
