package com.ssafy.guseul.domain.usecase.board

import com.ssafy.guseul.domain.entity.board.IndivisualBoardEntity
import com.ssafy.guseul.domain.repository.BoardRepository
import com.ssafy.guseul.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPostDetailUseCase @Inject constructor(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository
){
    suspend operator fun invoke(postId: Int): IndivisualBoardEntity {
        val board = boardRepository.getPost(postId)
        val user = userRepository.getUserInfo()
        return IndivisualBoardEntity(board, board.userId == user.userId)
    }
}