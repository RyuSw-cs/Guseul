package com.ssafy.guseul.data.remote.datasource.place

import com.ssafy.guseul.data.remote.datasource.place.model.AddressResponse
import com.ssafy.guseul.data.remote.service.PlaceApiService
import retrofit2.Response
import javax.inject.Inject

class PlaceRemoteDatasourceImpl @Inject constructor(
    private val placeApiService: PlaceApiService
) : PlaceRemoteDatasource {

    override suspend fun getAddressByLatLng(
        longitude: String,
        latitude: String
    ): AddressResponse {
        return placeApiService.getCurrentAddress(longitude, latitude)
    }

}