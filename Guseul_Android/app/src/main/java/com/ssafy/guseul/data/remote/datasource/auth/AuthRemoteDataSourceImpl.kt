package com.ssafy.guseul.data.remote.datasource.auth

import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTRequest
import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTResponse
import com.ssafy.guseul.data.remote.service.AuthApiService
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRemoteDataSource {
    override suspend fun postJWTWithKakao(accessToken: AuthJWTRequest): Response<AuthJWTResponse> {
        return authApiService.postKakaoLogin(accessToken)
    }
}