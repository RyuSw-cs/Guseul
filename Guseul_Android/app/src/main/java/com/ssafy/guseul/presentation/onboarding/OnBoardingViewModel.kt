package com.ssafy.guseul.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.guseul.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val testUseCase: AuthUseCase
) : ViewModel() {

    private val _isLoggedIn =  MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

//    fun requestLogin(body: AuthRequest) = viewModelScope.launch {
//        _isLoggedIn.emit(requestLoginUseCase(body))
//    }

    fun test() = viewModelScope.launch {

    }
}