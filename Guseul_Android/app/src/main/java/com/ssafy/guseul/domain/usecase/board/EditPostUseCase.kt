package com.ssafy.guseul.domain.usecase.board

import com.ssafy.guseul.data.remote.datasource.board.BoardRequest
import com.ssafy.guseul.domain.repository.BoardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditPostUseCase @Inject constructor(
    private val boardRepository: BoardRepository
){
    suspend operator fun invoke(postId: Int, body: BoardRequest) = boardRepository.editPost(postId, body)
}