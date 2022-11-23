package com.ssafy.guseul.data.remote.service

import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTRequest
import com.ssafy.guseul.data.remote.datasource.auth.model.AuthJWTResponse
import com.ssafy.guseul.data.remote.datasource.user.model.UserRequest
import com.ssafy.guseul.data.remote.datasource.user.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @GET("/api/user/onboarding")
    suspend fun getUserInfo() : Response<UserResponse>

    @POST("/api/user/onboarding")
    suspend fun postUserAdditionalInfo(
        @Body userRequest : UserRequest
    ) : UserResponse?
}