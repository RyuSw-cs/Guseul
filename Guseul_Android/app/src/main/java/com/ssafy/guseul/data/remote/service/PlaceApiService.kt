package com.ssafy.guseul.data.remote.service

import com.ssafy.guseul.data.remote.datasource.place.model.AddressResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceApiService {
    @GET("/v2/local/geo/coord2address.json")
    fun getCurrentAddress(
        @Query("x") longitude : String,
        @Query("y") latitude : String
    ) : AddressResponse
}