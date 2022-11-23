package com.ssafy.guseul.domain.usecase.user

import com.ssafy.guseul.domain.entity.user.BoardEntity
import com.ssafy.guseul.domain.repository.UserRepository
import javax.inject.Inject

class GetUserHistoryUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: Int): List<BoardEntity> {
        return userRepository.getUserHistory(userId)
    }
}