package com.ssafy.guseul.domain.usecase.place

import com.ssafy.guseul.domain.entity.place.AddressEntity
import com.ssafy.guseul.domain.repository.PlaceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPlaceUseCase @Inject constructor(private val placeRepository: PlaceRepository) {

}