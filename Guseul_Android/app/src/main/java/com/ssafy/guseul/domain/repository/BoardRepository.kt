package com.ssafy.guseul.domain.repository

import com.ssafy.guseul.data.remote.datasource.board.BoardRequest
import com.ssafy.guseul.domain.entity.board.BoardEntity

interface BoardRepository {

    suspend fun getPosts(): List<BoardEntity>

    suspend fun createPost(body: BoardRequest): Boolean

    suspend fun getPost(postId: Int): BoardEntity

    suspend fun deletePost(postId: Int): String

    suspend fun editPost(postId: Int, body: BoardRequest): Boolean
}