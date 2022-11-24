package com.ssafy.guseul.presentation.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.guseul.domain.entity.place.AddressEntity
import com.ssafy.guseul.domain.entity.place.PlaceEntity
import com.ssafy.guseul.domain.usecase.place.GetAddressUseCase
import com.ssafy.guseul.domain.usecase.place.GetPlaceUseCase
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressUseCase,
    private val getPlaceUseCase: GetPlaceUseCase
) :
    ViewModel() {
    private val _currentAddress = MutableLiveData<ViewState<AddressEntity>>()
    val currentAddress: LiveData<ViewState<AddressEntity>> get() = _currentAddress

    private val _markerList = MutableLiveData<ViewState<List<PlaceEntity>>>()
    val markerList: LiveData<ViewState<List<PlaceEntity>>> get() = _markerList

    fun getCurrentAddress(longitude: Double, latitude: Double) = viewModelScope.launch {
        _currentAddress.value = ViewState.Loading()
        try {
            val response = getAddressUseCase.getCurrentAddress(longitude, latitude)
            _currentAddress.value = ViewState.Success(response)
        } catch (e: Exception) {
            _currentAddress.value = ViewState.Error(e.message)
        }
    }

    fun getMarkerListByKeyword(
        keyword: String,
        longitude: Double,
        latitude: Double,
        category: Set<String>
    ) = viewModelScope.launch {
        _markerList.value = ViewState.Loading()
        try {
            val response = getPlaceUseCase.getMarker(keyword, longitude, latitude, category)
            _markerList.value = ViewState.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            _markerList.value = ViewState.Error(e.message)
        }
    }
}