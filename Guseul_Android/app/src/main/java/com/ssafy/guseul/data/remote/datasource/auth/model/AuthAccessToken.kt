package com.ssafy.guseul.data.remote.datasource.auth.model

import com.google.gson.annotations.SerializedName

data class AuthAccessToken(
    @SerializedName("accessToken")
    var accessToken : String
)