package com.ssafy.guseul.presentation.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.guseul.domain.entity.place.AddressEntity
import com.ssafy.guseul.domain.usecase.place.GetAddressUseCase
import com.ssafy.guseul.domain.usecase.place.GetPlaceUseCase
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(private val getAddressUseCase: GetAddressUseCase) :
    ViewModel() {
    private val _currentAddress = MutableLiveData<ViewState<AddressEntity>>()
    val currentAddress get() = _currentAddress

    fun getCurrentAddress(longitude: Double, latitude: Double) = viewModelScope.launch {
        _currentAddress.value = ViewState.Loading()
        try {
            val response = getAddressUseCase.getCurrentAddress(longitude, latitude)
            _currentAddress.value = ViewState.Success(response)
        } catch (e: Exception) {
            _currentAddress.value = ViewState.Error(e.message)
        }
    }
}