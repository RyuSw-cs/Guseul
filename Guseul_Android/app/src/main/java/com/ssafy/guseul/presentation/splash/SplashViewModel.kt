package com.ssafy.guseul.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.guseul.domain.entity.user.UserEntity
import com.ssafy.guseul.domain.usecase.user.GetUserUseCase
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) :
    ViewModel() {
    private val _userInfo = MutableLiveData<ViewState<UserEntity>>()
    val userInfo get() = _userInfo

    fun getUserInfo() = viewModelScope.launch {
        _userInfo.value = ViewState.Loading()
        try {
            val response = getUserUseCase.getUserInfo()
            _userInfo.value = ViewState.Success(response)
        } catch (e: Exception) {
            _userInfo.value = ViewState.Error(e.message)
        }
    }
}