package com.ssafy.guseul.domain.usecase.board

import com.ssafy.guseul.domain.entity.board.BoardEntity
import com.ssafy.guseul.domain.repository.BoardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostDetailUseCase @Inject constructor(
    private val boardRepository: BoardRepository
){
    suspend operator fun invoke(postId: Int): BoardEntity {
        return boardRepository.getPost(postId)
    }
}