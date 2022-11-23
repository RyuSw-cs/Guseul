package com.ssafy.guseul.data.remote.datasource.place

import com.ssafy.guseul.data.remote.datasource.place.model.AddressResponse
import com.ssafy.guseul.data.remote.service.PlaceApiService
import retrofit2.Response
import javax.inject.Inject

interface PlaceRemoteDatasource {
    suspend fun getAddressByLatLng(longitude : String, latitude : String) : AddressResponse
}