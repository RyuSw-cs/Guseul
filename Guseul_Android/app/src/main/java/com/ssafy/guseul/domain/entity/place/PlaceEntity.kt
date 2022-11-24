package com.ssafy.guseul.domain.entity.place

data class PlaceEntity(
    val placeName : String,
    var phoneNumber : String?,
    val address : String,
    val placeCategory : String,
    val latitude : Double,
    val longitude : Double
)