package com.ssafy.guseul.data.remote.datasource.auth

import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTRequest
import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTResponse
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun postJWTWithKakao(
        accessToken : AuthJWTRequest
    ): Response<AuthJWTResponse>
}