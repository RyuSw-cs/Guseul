package com.ssafy.guseul.data.remote.datasource.board

interface BoardRemoteDataSource {

    suspend fun getPosts(): List<BoardResponse>

    suspend fun createPost(body: BoardRequest): Boolean

    suspend fun getPost(postId: Int): BoardResponse

    suspend fun deletePost(postId: Int): String
}