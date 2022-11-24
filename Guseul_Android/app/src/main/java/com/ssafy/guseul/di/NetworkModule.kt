package com.ssafy.guseul.di

import android.content.Context
import com.ssafy.guseul.AuthInterceptorClient
import com.ssafy.guseul.KakaoInterceptorClient
import com.ssafy.guseul.NoAuthInterceptorClient
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.Constants.BASE_URL
import com.ssafy.guseul.common.util.Constants.KAKAO_BASE_URL
import com.ssafy.guseul.data.local.datasource.SharedPreferences
import com.ssafy.guseul.data.remote.AuthInterceptor
import com.ssafy.guseul.data.remote.KakaoAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @NoAuthInterceptorClient
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    @Provides
    @Singleton
    @AuthInterceptorClient
    fun provideAuthHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val authInterceptor = AuthInterceptor(SharedPreferences(context))
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @KakaoInterceptorClient
    fun provideKakaoHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val authInterceptor = KakaoAuthInterceptor(context.getString(R.string.KAKAO_REST_APP_KEY))
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(authInterceptor)
            .build()
    }


    @Provides
    @Singleton
    @NoAuthInterceptorClient
    fun provideRetrofit(
        @NoAuthInterceptorClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    @AuthInterceptorClient
    fun provideAuthRetrofit(
        @AuthInterceptorClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @KakaoInterceptorClient
    fun provideKakaoRetrofit(
        @KakaoInterceptorClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}