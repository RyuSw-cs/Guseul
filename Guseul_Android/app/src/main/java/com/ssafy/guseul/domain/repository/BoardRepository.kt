package com.ssafy.guseul.domain.repository

import com.ssafy.guseul.data.remote.datasource.board.BoardResponse

interface BoardRepository {

    suspend fun getPosts(): BoardResponse

}