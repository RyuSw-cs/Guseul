package com.ssafy.guseul.domain.usecase.auth

import com.ssafy.guseul.domain.entity.auth.AuthJWTRequest
import com.ssafy.guseul.domain.entity.auth.AuthUserAccessToken
import com.ssafy.guseul.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun getUserAccessToken(kakaoAccessToken: String): AuthUserAccessToken {
        val request = AuthJWTRequest(kakaoAccessToken)
        return authRepository.getJWTWithKakao(request)
    }
}
