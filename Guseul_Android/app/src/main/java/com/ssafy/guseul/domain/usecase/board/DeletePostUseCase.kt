package com.ssafy.guseul.domain.usecase.board

import com.ssafy.guseul.domain.repository.BoardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeletePostUseCase @Inject constructor(
    private val boardRepository: BoardRepository
){
    suspend operator fun invoke(postId: Int): String {
        return boardRepository.deletePost(postId)
    }
}