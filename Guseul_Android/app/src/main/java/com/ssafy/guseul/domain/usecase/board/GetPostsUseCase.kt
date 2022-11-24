package com.ssafy.guseul.domain.usecase.board

import com.ssafy.guseul.domain.entity.board.BoardEntity
import com.ssafy.guseul.domain.repository.BoardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostsUseCase @Inject constructor(
    private val boardRepository: BoardRepository
){
    suspend fun getPosts(): List<BoardEntity> {
        return boardRepository.getPosts()
    }
}