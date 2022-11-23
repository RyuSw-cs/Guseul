package com.ssafy.guseul.data.remote.datasource.user.model

import com.google.gson.annotations.SerializedName

data class UserRequest(
    val nickname : String,
    val address : String
)