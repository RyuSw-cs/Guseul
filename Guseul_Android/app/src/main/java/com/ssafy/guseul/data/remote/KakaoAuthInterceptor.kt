package com.ssafy.guseul.data.remote

import com.ssafy.guseul.ApplicationClass.Companion.preferences
import com.ssafy.guseul.data.local.datasource.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class KakaoAuthInterceptor(private val kakaoKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "KakaoAK $kakaoKey").build()

        return chain.proceed(request)
    }
}
