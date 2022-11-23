package com.ssafy.guseul.domain.repository

import com.ssafy.guseul.domain.entity.place.AddressEntity

interface PlaceRepository {
    suspend fun getDefaultAddress(longitude : String, latitude : String) : AddressEntity
}