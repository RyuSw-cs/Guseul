package com.ssafy.guseul.data.remote.datasource.base

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @SerializedName("statusCode")
    var statusCode: Int? = null,
    @SerializedName("resMessage")
    var resMessage: String? = null,
    @SerializedName("data")
    var data: T? = null,
)