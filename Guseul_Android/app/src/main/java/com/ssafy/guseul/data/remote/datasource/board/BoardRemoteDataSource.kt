package com.ssafy.guseul.data.remote.datasource.board

import com.ssafy.guseul.data.remote.datasource.base.BaseResponse

interface BoardRemoteDataSource {

    suspend fun getPosts(): BaseResponse<List<BoardResponse>>

}