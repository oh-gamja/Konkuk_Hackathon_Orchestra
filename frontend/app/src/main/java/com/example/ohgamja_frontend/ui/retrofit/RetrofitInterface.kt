package com.example.ohgamja_frontend.ui.retrofit

import com.example.ohgamja_frontend.ui.retrofit.LoginRequest
import com.example.ohgamja_frontend.ui.retrofit.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/games/all")
    fun GetAllGames(
    ): Call<BaseResponse<GamesResponse>>

    @GET("/likes/top")
    fun GetTopGameList(
    ): Call<BaseResponse<TopGameResponse>>


    @GET("/likes")
    fun GetLikes(
    ): Call<BaseResponse<GamesResponse>>

    @GET("/playlists")
    fun GetPlaylist(
    ): Call<BaseResponse<PlaylistResponse>>

    @DELETE("/auth/signout")
    fun KaKaoSingout(
    ): Call<BaseResponse<SignoutResponse>>

    @GET("/games/detail")
    fun GetGameDetail(
        @Query("gameId") gameId: Int
    ): Call<BaseResponse<DetailResponse>>

    @POST("/likes/add")
    fun AddLike(
        @Query("gameId") gameId: Int
    ): Call<BaseResponse<resultResponse>>

    @GET("/games/random")
    fun GetRandomGame(
    ): Call<BaseResponse<DetailResponse>>

    @GET("/playlists/detail")
    fun GetPlaylistDetail(
        @Query("playlistId") playlistId: Int
    ): Call<BaseResponse<GamesResponse>>

    @GET("/games")
    fun GetSearch(
        @Query("difficulty") difficulty: String?,
        @Query("category") category : String?,
        @Query("name") name : String?,
        @Query("headCount") headCount : Int
    ) : Call<BaseResponse<SearchResponse>>

    @DELETE("/likes/remove")
    fun DeleteLike(
        @Query("gameId") gameId: Int
    ): Call<BaseResponse<resultResponse>>

    @POST("/playlists")
    fun AddList(
        @Body requestBody: PlaylistRequest
    ) : Call<BaseResponse<String>>

    @HTTP(method = "DELETE", path = "/playlists/remove", hasBody = true)
    fun DeleteList(
        @Body requestBody: DeletePlaylistRequest
    ): Call<BaseResponse<String>>


    @POST("/playlists/putInPlaylist")
    fun AddToList(
        @Query("gameId") gameId: Int,
        @Query("playlistId") playlistId: Int,
    ) : Call<BaseResponse<String>>


    @GET("/playlists/playlistInfo")
    fun GetListInfo(
        @Query("gameId") gameId: Int
    ): Call<BaseResponse<playlistInfoList>>


    @HTTP(method = "DELETE", path = "/playlists/detail/remove", hasBody = true)
    fun DeleteFromList(
        @Query("gameId") gameId: Int,
        @Query("playlistId") playlistId: Int,
    ): Call<BaseResponse<String>>


    @HTTP(method = "DELETE", path = "/playlists/remove", hasBody = true)
    fun DeleteList(
        @Query("playlistId") playlistId: Int,
    ): Call<BaseResponse<String>>

}




interface RetrofitLoginInterface {

    @POST("/auth/kakao")
    fun KaKaoLogin(
        @Body requestBody: LoginRequest
    ): Call<BaseResponse<LoginResponse>>


}