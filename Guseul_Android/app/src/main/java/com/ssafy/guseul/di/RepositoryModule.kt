package com.ssafy.guseul.di

import com.ssafy.guseul.data.remote.datasource.auth.AuthRemoteDataSourceImpl
import com.ssafy.guseul.data.remote.datasource.board.BoardRemoteDataSourceImpl
import com.ssafy.guseul.data.remote.datasource.place.PlaceRemoteDatasourceImpl
import com.ssafy.guseul.data.remote.datasource.user.UserRemoteDataSourceImpl
import com.ssafy.guseul.data.remote.repository.AuthRepositoryImpl
import com.ssafy.guseul.data.remote.repository.BoardRepositoryImpl
import com.ssafy.guseul.data.remote.repository.PlaceRepositoryImpl
import com.ssafy.guseul.data.remote.repository.UserRepositoryImpl
import com.ssafy.guseul.domain.repository.AuthRepository
import com.ssafy.guseul.domain.repository.BoardRepository
import com.ssafy.guseul.domain.repository.PlaceRepository
import com.ssafy.guseul.domain.repository.UserRepository
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
    ): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSourceImpl)
    }

    @Provides
    @Singleton
    fun provideBoardRepository(
        boardRemoteDataSourceImpl: BoardRemoteDataSourceImpl,
    ) : BoardRepository {
        return BoardRepositoryImpl(boardRemoteDataSourceImpl)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRepository {
        return UserRepositoryImpl(userRemoteDataSourceImpl)
    }

    @Provides
    @Singleton
    fun providePlaceRepository(
        placeRemoteDataSourceImpl: PlaceRemoteDatasourceImpl
    ): PlaceRepository {
        return PlaceRepositoryImpl(placeRemoteDataSourceImpl)
    }
}