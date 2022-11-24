package com.ssafy.guseul.domain.usecase.place

import com.ssafy.guseul.domain.entity.place.AddressEntity
import com.ssafy.guseul.domain.entity.place.PlaceEntity
import com.ssafy.guseul.domain.repository.PlaceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPlaceUseCase @Inject constructor(private val placeRepository: PlaceRepository) {
    suspend fun getMarker(
        query: String,
        longitude: Double,
        latitude: Double,
        filter: Set<String>
    ): List<PlaceEntity> {
        val resultList = mutableListOf<PlaceEntity>()
        filter.forEach { category ->
            resultList.addAll(
                placeRepository.getPlaceByKeyword(
                    query,
                    longitude.toString(),
                    latitude.toString(),
                    category
                )
            )
        }
        return resultList
    }
}