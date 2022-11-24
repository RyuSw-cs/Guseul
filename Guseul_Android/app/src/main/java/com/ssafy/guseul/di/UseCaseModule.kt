package com.ssafy.guseul.di

import com.ssafy.guseul.domain.repository.AuthRepository
import com.ssafy.guseul.domain.repository.BoardRepository
import com.ssafy.guseul.domain.repository.UserRepository
import com.ssafy.guseul.domain.usecase.auth.GetTokenUseCase
import com.ssafy.guseul.domain.usecase.board.CreatePostUseCase
import com.ssafy.guseul.domain.usecase.board.GetPostsUseCase
import com.ssafy.guseul.domain.usecase.user.EditUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetTokenUseCase(authRepository: AuthRepository) : GetTokenUseCase {
        return GetTokenUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideGetPostsUseCase(boardRepository: BoardRepository) : GetPostsUseCase {
        return GetPostsUseCase(boardRepository)
    }

    @Singleton
    @Provides
    fun createPostUseCase(boardRepository: BoardRepository) : CreatePostUseCase {
        return CreatePostUseCase(boardRepository)
    }

    @Singleton
    @Provides
    fun provideEditUserUseCase(userRepository: UserRepository) : EditUserUseCase {
        return EditUserUseCase(userRepository)
    }
}