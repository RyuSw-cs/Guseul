package com.ssafy.guseul.presentation.login.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.guseul.domain.entity.auth.AuthUserAccessToken
import com.ssafy.guseul.domain.usecase.auth.AuthUseCase
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _accessToken = MutableLiveData<ViewState<AuthUserAccessToken>>()
    val accessToken get() = _accessToken

    fun getJWTWithKakao(kakaoAccessToken: String) = viewModelScope.launch {
        _accessToken.value = ViewState.Loading()
        try {
            val response = authUseCase.getUserAccessToken(kakaoAccessToken)
            _accessToken.value = ViewState.Success(response)
        } catch (e: Exception) {
            _accessToken.value = ViewState.Error(e.message, null)
        }
    }
}