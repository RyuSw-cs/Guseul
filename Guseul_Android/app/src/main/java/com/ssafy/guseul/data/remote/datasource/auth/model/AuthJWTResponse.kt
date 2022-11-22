package com.ssafy.guseul.data.remote.datasource.auth.model

import com.google.gson.annotations.SerializedName
import com.ssafy.guseul.data.remote.datasource.base.BaseResponse
import com.ssafy.guseul.data.remote.datasource.base.DataToDomainMapper
import com.ssafy.guseul.domain.entity.auth.AuthUserAccessToken

data class AuthJWTResponse(
    @SerializedName("statusCode")
    var statusCode: Int? = null,
    @SerializedName("resMessage")
    var resMessage: String? = null,
    @SerializedName("accessToken")
    var accessToken: String? = null
) : DataToDomainMapper<AuthUserAccessToken> {
    override fun toDomainModel(): AuthUserAccessToken {
        return AuthUserAccessToken(
            accessToken = accessToken ?: ""
        )
    }
}