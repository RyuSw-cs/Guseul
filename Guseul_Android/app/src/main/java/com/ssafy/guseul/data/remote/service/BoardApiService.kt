package com.ssafy.guseul.data.remote.service

import com.ssafy.guseul.data.remote.datasource.base.BaseResponse
import com.ssafy.guseul.data.remote.datasource.board.BoardRequest
import com.ssafy.guseul.data.remote.datasource.board.BoardResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BoardApiService {

    @GET("/api/post")
    suspend fun getPosts(): BaseResponse<List<BoardResponse>>

    @POST("/api/post")
    suspend fun createPost(@Body body: BoardRequest): BaseResponse<Int>
}