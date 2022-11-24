package com.ssafy.guseul.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.guseul.domain.entity.board.BoardEntity
import com.ssafy.guseul.domain.usecase.user.GetUserHistoryUseCase
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getUserHistoryUseCase: GetUserHistoryUseCase
) : ViewModel() {
    private val _boardEntity = MutableLiveData<ViewState<List<BoardEntity>>>()
    val boardEntity: LiveData<ViewState<List<BoardEntity>>> = _boardEntity

    fun getUserHistory(userId: Int) = viewModelScope.launch {
        _boardEntity.value = ViewState.Loading()
        try {
            val response = getUserHistoryUseCase(userId)
            _boardEntity.value = ViewState.Success(response)
        } catch (e: Exception) {
            _boardEntity.value = ViewState.Error(e.message, null)
        }
    }
}