package com.ssafy.guseul.data.remote.repository

import com.ssafy.guseul.ApplicationClass
import com.ssafy.guseul.data.remote.datasource.auth.AuthRemoteDataSource
import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTRequest
import com.ssafy.guseul.domain.entity.auth.AuthUserAccessToken
import com.ssafy.guseul.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun getJWTWithKakao(accessToken: AuthJWTRequest): AuthUserAccessToken {
        val response = authRemoteDataSource.postJWTWithKakao(accessToken)

        if(response.isSuccessful){
            val result = response.body()
            ApplicationClass.preferences.accessToken = result?.jwt?.accessToken
        }

        return response.body()?.toDomainModel()!!
    }
}