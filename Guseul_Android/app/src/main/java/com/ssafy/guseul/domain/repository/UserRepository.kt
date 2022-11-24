package com.ssafy.guseul.domain.repository

import com.ssafy.guseul.data.remote.datasource.user.model.UserRequest
import com.ssafy.guseul.domain.entity.board.BoardEntity
import com.ssafy.guseul.domain.entity.user.UserEntity

interface UserRepository {
    suspend fun editUserAdditionalInfo(userRequest : UserRequest) : UserEntity
    suspend fun getUserInfo() : UserEntity
    suspend fun getUserHistory(userId: Int) : List<BoardEntity>
}