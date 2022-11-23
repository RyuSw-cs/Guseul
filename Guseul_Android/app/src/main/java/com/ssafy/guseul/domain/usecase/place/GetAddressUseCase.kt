package com.ssafy.guseul.domain.usecase.place

import com.ssafy.guseul.domain.entity.place.AddressEntity
import com.ssafy.guseul.domain.repository.PlaceRepository
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(private val placeRepository: PlaceRepository) {
    suspend fun getCurrentAddress(longitude: Double, latitude: Double): AddressEntity {
        return placeRepository.getDefaultAddress(longitude.toString(), latitude.toString())
    }
}