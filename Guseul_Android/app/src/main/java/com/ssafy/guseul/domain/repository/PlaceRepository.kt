package com.ssafy.guseul.domain.repository

import com.ssafy.guseul.data.remote.datasource.place.model.PlaceResponse
import com.ssafy.guseul.domain.entity.place.AddressEntity

interface PlaceRepository {
    suspend fun getDefaultAddress(longitude : String, latitude : String) : AddressEntity
    suspend fun getPlaceByKeyword(query: String, longitude: String, latitude: String) : PlaceResponse
}