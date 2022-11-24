package com.ssafy.guseul.data.remote.datasource.user

import com.ssafy.guseul.data.remote.datasource.board.BoardResponse
import com.ssafy.guseul.data.remote.datasource.user.model.UserRequest
import com.ssafy.guseul.data.remote.datasource.user.model.UserResponse
import com.ssafy.guseul.data.remote.service.UserApiService
import com.ssafy.guseul.domain.entity.board.BoardEntity
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApiService: UserApiService
) : UserRemoteDataSource{
    override suspend fun postUserAdditionalInfo(request: UserRequest): UserResponse? {
        return userApiService.postUserAdditionalInfo(request)
    }

    override suspend fun getUserInfo(): Response<UserResponse> {
        return userApiService.getUserInfo()
    }

    override suspend fun getUserHistory(userId: Int): List<BoardResponse> {
        return userApiService.getUserHistory(userId).data ?: listOf()
    }
}