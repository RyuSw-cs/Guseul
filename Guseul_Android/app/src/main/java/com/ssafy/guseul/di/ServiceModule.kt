package com.ssafy.guseul.di

import com.ssafy.guseul.NoAuthInterceptorClient
import com.ssafy.guseul.data.remote.service.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

//파라미터가 인터페이스 형태 -> abstract
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    //파라미터가 retrofit -> 직접 구현해야하는 인스턴스 -> provide
    //파라미터가 인터페이스 구현체 -> binds
    @Provides
    @Singleton
    fun provideAuthApiService(
        @NoAuthInterceptorClient retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

}