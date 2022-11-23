package com.ssafy.guseul.data.remote.datasource.board

import android.util.Log
import com.ssafy.guseul.data.remote.service.BoardApiService
import javax.inject.Inject

class BoardRemoteDataSourceImpl @Inject constructor(
    private val boardApiService: BoardApiService
) : BoardRemoteDataSource {

    override suspend fun getPosts(): List<BoardResponse> {
        return boardApiService.getPosts().data ?: listOf()
    }

    override suspend fun createPost(body: BoardRequest): Boolean {
        Log.d("asdf", "createPost: ${body}")

        val statusCode = boardApiService.createPost(body).statusCode
        return statusCode == 200
    }
}