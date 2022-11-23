package com.ssafy.guseul.data.remote.repository

import com.ssafy.guseul.data.remote.datasource.user.UserRemoteDataSource
import com.ssafy.guseul.data.remote.datasource.user.model.UserRequest
import com.ssafy.guseul.domain.entity.user.UserEntity
import com.ssafy.guseul.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository{
    override suspend fun editUserAdditionalInfo(userRequest: UserRequest): UserEntity {
        val response = userRemoteDataSource.postUserAdditionalInfo(userRequest)
        return response.body()?.toDomainModel()!!
    }
}