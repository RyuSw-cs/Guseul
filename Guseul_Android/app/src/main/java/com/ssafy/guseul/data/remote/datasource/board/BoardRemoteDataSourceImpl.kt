package com.ssafy.guseul.data.remote.datasource.board

import com.ssafy.guseul.data.remote.service.BoardApiService
import javax.inject.Inject

class BoardRemoteDataSourceImpl @Inject constructor(
    private val boardApiService: BoardApiService
) : BoardRemoteDataSource {

    override suspend fun getPosts(): List<BoardResponse> {
        return boardApiService.getPosts().data ?: listOf()
    }

    override suspend fun createPost(body: BoardRequest): Boolean {
        val statusCode = boardApiService.createPost(body).statusCode
        return statusCode == 200
    }

    override suspend fun getPost(postId: Int): BoardResponse {
        return boardApiService.getPost(postId).data!!
    }

    override suspend fun deletePost(postId: Int): String {
        return boardApiService.deletePost(postId).resMessage!!
    }

}