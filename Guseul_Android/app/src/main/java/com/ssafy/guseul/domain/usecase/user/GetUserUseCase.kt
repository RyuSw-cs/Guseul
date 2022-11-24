package com.ssafy.guseul.domain.usecase.user

import com.ssafy.guseul.data.remote.datasource.user.model.UserResponse
import com.ssafy.guseul.domain.entity.auth.AuthUserAccessToken
import com.ssafy.guseul.domain.entity.user.UserEntity
import com.ssafy.guseul.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun getUserInfo(): UserEntity {
        return userRepository.getUserInfo()
    }
}