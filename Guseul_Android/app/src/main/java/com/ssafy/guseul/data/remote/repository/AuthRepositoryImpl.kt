package com.ssafy.guseul.data.remote.repository

import android.util.Log
import com.ssafy.guseul.ApplicationClass
import com.ssafy.guseul.data.local.datasource.SharedPreferences
import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTResponse
import com.ssafy.guseul.data.remote.datasource.base.BaseResponse
import com.ssafy.guseul.domain.datasource.remote.AuthRemoteDataSource
import com.ssafy.guseul.domain.entity.auth.AuthJWTRequest
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
            ApplicationClass.preferences.accessToken = result?.accessToken
        }

        return response.body()?.toDomainModel()!!
    }
}