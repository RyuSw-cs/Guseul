package com.ssafy.guseul.data.remote.repository

import android.util.Log
import com.ssafy.guseul.data.remote.datasource.place.PlaceRemoteDatasource
import com.ssafy.guseul.data.remote.datasource.place.model.PlaceResponse
import com.ssafy.guseul.domain.entity.place.AddressEntity
import com.ssafy.guseul.domain.entity.place.PlaceEntity
import com.ssafy.guseul.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(private val placeRemoteDatasource: PlaceRemoteDatasource) :
    PlaceRepository {
    override suspend fun getDefaultAddress(longitude: String, latitude: String): AddressEntity {
        return placeRemoteDatasource.getAddressByLatLng(longitude, latitude).toDomainModel()
    }

    override suspend fun getPlaceByKeyword(
        query: String,
        longitude: String,
        latitude: String,
        category: String
    ): List<PlaceEntity> {
        return placeRemoteDatasource.getPlaceByKeyword(query, longitude, latitude, category).toDomainModel()
    }
}