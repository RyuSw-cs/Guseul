package com.ssafy.guseul.data.remote.service

import com.ssafy.guseul.data.remote.datasource.base.BaseResponse
import com.ssafy.guseul.data.remote.datasource.board.BoardResponse
import retrofit2.http.GET

interface BoardApiService {

    @GET("/api/post")
    suspend fun getPosts(): BaseResponse<List<BoardResponse>>
}