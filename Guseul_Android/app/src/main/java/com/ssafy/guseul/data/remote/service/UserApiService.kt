package com.ssafy.guseul.data.remote.service


import com.ssafy.guseul.data.remote.datasource.base.BaseResponse
import com.ssafy.guseul.data.remote.datasource.board.BoardResponse
import com.ssafy.guseul.data.remote.datasource.user.model.UserRequest
import com.ssafy.guseul.data.remote.datasource.user.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {
    @GET("/api/user/onboarding")
    suspend fun getUserInfo() : Response<UserResponse>

    @POST("/api/user/onboarding")
    suspend fun postUserAdditionalInfo(
        @Body userRequest : UserRequest
    ) : UserResponse?

    @GET("/api/post/user/{userId}")
    suspend fun getUserHistory(
        @Path("userId") userId: Int
    ) : BaseResponse<List<BoardResponse>>
}