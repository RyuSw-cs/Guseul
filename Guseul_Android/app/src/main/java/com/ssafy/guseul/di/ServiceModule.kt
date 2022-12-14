package com.ssafy.guseul.di

import com.ssafy.guseul.AuthInterceptorClient
import com.ssafy.guseul.KakaoInterceptorClient
import com.ssafy.guseul.NoAuthInterceptorClient
import com.ssafy.guseul.data.remote.service.AuthApiService
import com.ssafy.guseul.data.remote.service.BoardApiService
import com.ssafy.guseul.data.remote.service.PlaceApiService

import com.ssafy.guseul.data.remote.service.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideAuthApiService(
        @NoAuthInterceptorClient retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideBoardApiService(
        @AuthInterceptorClient retrofit: Retrofit): BoardApiService =
        retrofit.create(BoardApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiService(
        @AuthInterceptorClient retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun providePlaceApiService(
        @KakaoInterceptorClient retrofit: Retrofit): PlaceApiService =
        retrofit.create(PlaceApiService::class.java)

}