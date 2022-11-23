package com.ssafy.guseul.data.remote.datasource.board

import com.ssafy.guseul.data.remote.service.BoardApiService
import javax.inject.Inject

class BoardRemoteDataSourceImpl @Inject constructor(
    private val boardApiService: BoardApiService
) : BoardRemoteDataSource {

    override suspend fun getPosts(): List<BoardResponse> {
        return boardApiService.getPosts()
    }

    override suspend fun createPost(body: BoardRequest): Boolean {
        return boardApiService.createPost().statusCode == 200
    }
}