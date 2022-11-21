package com.ssafy.guseul.data.remote.datasource.auth

import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTResponse
import com.ssafy.guseul.data.remote.datasource.base.BaseResponse
import com.ssafy.guseul.data.remote.service.AuthApiService
import com.ssafy.guseul.domain.datasource.remote.AuthRemoteDataSource
import com.ssafy.guseul.domain.entity.auth.AuthJWTRequest
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRemoteDataSource {
    override suspend fun postJWTWithKakao(accessToken: AuthJWTRequest): Response<AuthJWTResponse> {
        return authApiService.postKakaoLogin(accessToken)
    }
}