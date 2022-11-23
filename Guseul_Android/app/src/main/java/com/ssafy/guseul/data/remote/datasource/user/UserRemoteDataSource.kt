package com.ssafy.guseul.data.remote.datasource.user

import com.ssafy.guseul.data.remote.datasource.user.model.UserRequest
import com.ssafy.guseul.data.remote.datasource.user.model.UserResponse
import com.ssafy.guseul.domain.entity.user.BoardEntity
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun postUserAdditionalInfo(request : UserRequest):UserResponse?
    suspend fun getUserInfo() : Response<UserResponse>
    suspend fun getUserHistory(userId: Int) : List<BoardEntity>
}