package com.ssafy.guseul.data.remote.service

import com.ssafy.guseul.data.remote.datasource.place.model.AddressResponse
import com.ssafy.guseul.data.remote.datasource.place.model.PlaceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlaceApiService {
    @GET("/v2/local/geo/coord2address.json")
    suspend fun getCurrentAddress(
        @Query("x") longitude : String,
        @Query("y") latitude : String
    ) : AddressResponse

    @GET("/v2/local/search/keyword.json")
    suspend fun getPlaceByKeyword(
        @Query("query") query : String,
        @Query("x") longitude : String,
        @Query("y") latitude: String,
        @Query("radius") radius : Int = 1000
    ) : PlaceResponse
}