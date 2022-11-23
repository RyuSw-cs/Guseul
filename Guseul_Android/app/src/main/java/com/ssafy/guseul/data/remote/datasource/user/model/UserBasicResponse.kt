package com.ssafy.guseul.data.remote.datasource.user.model

import com.google.gson.annotations.SerializedName

data class UserBasicResponse(
    @SerializedName("id")
    var id : Int?,
    @SerializedName("nickname")
    var nickname : String?,
    @SerializedName("address")
    var address : String?,
    @SerializedName("stamps")
    var stamps : Int?,
    @SerializedName("createdAt")
    var createdAt : String?,
)