package com.example.bangwool.retrofit

import com.google.gson.annotations.SerializedName

data class MemberSignUpRequest(
    @SerializedName("email") val id: String,
    @SerializedName("name") val image: String,
    @SerializedName("nickname") val name: String,
    @SerializedName("password") val eng_name: String
)

