package com.ssafy.guseul.domain.repository

import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTResponse
import com.ssafy.guseul.domain.entity.auth.AuthJWTRequest
import com.ssafy.guseul.domain.entity.auth.AuthUserAccessToken

interface AuthRepository {
     suspend fun getJWTWithKakao(accessToken: AuthJWTRequest) : AuthUserAccessToken
}