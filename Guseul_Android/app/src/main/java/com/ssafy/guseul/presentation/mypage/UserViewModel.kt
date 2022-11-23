package com.ssafy.guseul.presentation.mypage

import androidx.lifecycle.LiveData
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
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _userEntity = MutableLiveData<ViewState<UserEntity>>()
    val userEntity: LiveData<ViewState<UserEntity>> = _userEntity

    var userId: Int = -1

    fun getUserInfo() = viewModelScope.launch {
        _userEntity.value = ViewState.Loading()
        try {
            val response = getUserUseCase.getUserInfo()
            _userEntity.value = ViewState.Success(response)
            userId = response.userId ?: -1
        } catch (e: Exception) {
            _userEntity.value = ViewState.Error(e.message, null)
        }
    }
}