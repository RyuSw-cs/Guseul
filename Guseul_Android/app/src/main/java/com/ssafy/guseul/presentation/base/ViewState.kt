package com.ssafy.guseul.presentation.base

sealed class ViewState<T>(
    val value: T? = null,
    val message: String? = null
) {
    //서버 연동 상태
    class Success<T>(data: T) : ViewState<T>(data)
    class Error<T>(message: String?, data: T? = null) : ViewState<T>(data, message)
    class Loading<T> : ViewState<T>()
}