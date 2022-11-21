package com.ssafy.guseul.data.di

import com.ssafy.guseul.data.remote.datasource.auth.AuthRemoteDataSourceImpl
import com.ssafy.guseul.data.remote.service.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideAuthDataSource(
        authApiService: AuthApiService
    ): AuthRemoteDataSourceImpl = AuthRemoteDataSourceImpl(authApiService)
}