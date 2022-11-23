package com.ssafy.guseul.presentation.join

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.guseul.domain.entity.user.UserEntity
import com.ssafy.guseul.domain.usecase.user.EditUserUseCase
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val editUserUseCase: EditUserUseCase
): ViewModel() {
    private val _userEntity = MutableLiveData<ViewState<UserEntity>>()
    val userEntity get() = _userEntity

    fun editUserAdditionalInfo(nickname : String, address : String) = viewModelScope.launch {
        _userEntity.value = ViewState.Loading()
        try{
            val response = editUserUseCase.editUserAdditionalInfo(nickname, address)
            _userEntity.value = ViewState.Success(response)
        }catch (e : Exception){
            _userEntity.value = ViewState.Error(e.message, null)
        }
    }
}