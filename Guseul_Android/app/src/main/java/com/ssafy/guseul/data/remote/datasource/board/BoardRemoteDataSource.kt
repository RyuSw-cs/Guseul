package com.ssafy.guseul.data.remote.datasource.board

interface BoardRemoteDataSource {

    suspend fun getPosts(): List<BoardResponse>

    suspend fun createPost(body: BoardRequest): Boolean
}