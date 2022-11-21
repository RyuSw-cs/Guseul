package com.ssafy.guseul.data.remote.service

import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTResponse
import com.ssafy.guseul.domain.entity.auth.AuthJWTRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/api/auth/login")
    suspend fun postKakaoLogin(
        @Body kakaoAccessToken : AuthJWTRequest
    ) : Response<AuthJWTResponse>
}