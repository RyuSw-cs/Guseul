package com.ssafy.guseul.di

import com.ssafy.guseul.data.remote.datasource.auth.AuthRemoteDataSourceImpl
import com.ssafy.guseul.data.remote.datasource.board.BoardRemoteDataSourceImpl
import com.ssafy.guseul.data.remote.datasource.place.PlaceRemoteDatasourceImpl
import com.ssafy.guseul.data.remote.datasource.user.UserRemoteDataSourceImpl
import com.ssafy.guseul.data.remote.service.AuthApiService
import com.ssafy.guseul.data.remote.service.PlaceApiService
import com.ssafy.guseul.data.remote.service.UserApiService
import com.ssafy.guseul.data.remote.service.BoardApiService
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

    @Provides
    @Singleton
    fun provideUserDataSource(
        userApiService: UserApiService
    ): UserRemoteDataSourceImpl = UserRemoteDataSourceImpl(userApiService)


    @Provides
    @Singleton
    fun providePlaceDataSource(
        placeApiService: PlaceApiService
    ): PlaceRemoteDatasourceImpl = PlaceRemoteDatasourceImpl(placeApiService)


    @Provides
    @Singleton
    fun provideBoardDataSource(
        boardApiService: BoardApiService
    ): BoardRemoteDataSourceImpl = BoardRemoteDataSourceImpl(boardApiService)

}