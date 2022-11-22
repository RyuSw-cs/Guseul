package com.ssafy.guseul.di

import com.ssafy.guseul.data.remote.datasource.auth.AuthRemoteDataSourceImpl
import com.ssafy.guseul.data.remote.repository.AuthRepositoryImpl
import com.ssafy.guseul.domain.datasource.remote.AuthRemoteDataSource
import com.ssafy.guseul.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl,
    ) : AuthRepository{
        return AuthRepositoryImpl(authRemoteDataSourceImpl)
    }

}