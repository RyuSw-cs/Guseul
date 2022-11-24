package com.ssafy.guseul.data.remote.service

import com.ssafy.guseul.data.remote.datasource.base.BaseResponse
import com.ssafy.guseul.data.remote.datasource.board.BoardRequest
import com.ssafy.guseul.data.remote.datasource.board.BoardResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BoardApiService {

    @GET("/api/post")
    suspend fun getPosts(): BaseResponse<List<BoardResponse>>

    @POST("/api/post")
    suspend fun createPost(@Body body: BoardRequest): BaseResponse<Int>

    @GET("/api/post/{postId}")
    suspend fun getPost(@Path("postId") postId: Int): BaseResponse<BoardResponse>

    @DELETE("/api/post/{postId}")
    suspend fun deletePost(@Path("postId") postId: Int): BaseResponse<String>

    @POST("/api/post/{postId}")
    suspend fun editPost(@Path("postId") postId: Int, @Body body: BoardRequest): BaseResponse<Int>
}