package com.ssafy.guseul.domain.repository

import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTRequest
import com.ssafy.guseul.domain.entity.auth.AuthUserAccessToken

interface AuthRepository {
     suspend fun getJWTWithKakao(accessToken: AuthJWTRequest) : AuthUserAccessToken
}