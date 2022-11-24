package com.ssafy.guseul.data.remote.repository

import com.ssafy.guseul.data.remote.datasource.board.BoardRemoteDataSource
import com.ssafy.guseul.data.remote.datasource.board.BoardRequest
import com.ssafy.guseul.domain.entity.board.BoardEntity
import com.ssafy.guseul.domain.repository.BoardRepository
import javax.inject.Inject

class BoardRepositoryImpl @Inject constructor(
    private val boardRemoteDataSource: BoardRemoteDataSource
) : BoardRepository {
    override suspend fun getPosts(): List<BoardEntity> {
        val response = boardRemoteDataSource.getPosts()
        return response.map { it.toDomainModel() }
    }

    override suspend fun createPost(body: BoardRequest): Boolean =
        boardRemoteDataSource.createPost(body)

    override suspend fun getPost(postId: Int): BoardEntity {
        return boardRemoteDataSource.getPost(postId).toDomainModel()
    }

    override suspend fun deletePost(postId: Int): String {
        return boardRemoteDataSource.deletePost(postId)
    }

    override suspend fun editPost(postId: Int, body: BoardRequest): Boolean =
        boardRemoteDataSource.editPost(postId, body)
}