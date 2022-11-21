package com.ssafy.guseul.domain.datasource.remote

import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTResponse
import com.ssafy.guseul.data.remote.datasource.base.BaseResponse
import com.ssafy.guseul.domain.entity.auth.AuthJWTRequest
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun postJWTWithKakao(
        accessToken : AuthJWTRequest
    ): Response<AuthJWTResponse>
}