package com.example.ohgamja_frontend.ui.retrofit

import com.example.ohgamja_frontend.ui.retrofit.LoginRequest
import com.example.ohgamja_frontend.ui.retrofit.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface RetrofitInterface {

    @GET("/games/all")
    fun GetAllGames(
    ): Call<BaseResponse<GamesResponse>>

}



interface RetrofitLoginInterface {

    @POST("/auth/kakao")
    fun KaKaoLogin(
        @Body requestBody: LoginRequest
    ): Call<BaseResponse<LoginResponse>>


}