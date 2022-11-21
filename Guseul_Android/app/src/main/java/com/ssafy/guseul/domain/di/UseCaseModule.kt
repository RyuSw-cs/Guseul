package com.ssafy.guseul.domain.di

import com.ssafy.guseul.domain.repository.AuthRepository
import com.ssafy.guseul.domain.usecase.auth.AuthUseCase
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
    fun provideAuthUseCase(authRepository: AuthRepository) : AuthUseCase{
        return AuthUseCase(authRepository)
    }
}