package com.ssafy.guseul.data.remote.datasource.place.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("meta")
    var meta: PlaceMetaResponse?,
    @SerializedName("documents")
    var document: List<PlaceDocumentResponse>?
)

data class PlaceMetaResponse(
    @SerializedName("total_count")
    var totalCount: Int?,
    @SerializedName("pageable_count")
    var pageableCount: Int?,
    @SerializedName("is_end")
    var isEnd: Boolean?,
    @SerializedName("same_name")
    var sameName: PlaceSameNameResponse?,
)

data class PlaceSameNameResponse(
    @SerializedName("region")
    var region: Array<String>?,
    @SerializedName("keyword")
    var keyword: String?,
    @SerializedName("selected_region")
    var selectedRegion: String?
)

data class PlaceDocumentResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("place_name")
    var placeName: String?,
    @SerializedName("category_name")
    var categoryName: String?,
    @SerializedName("category_group_code")
    var categoryGroupCode: String?,
    @SerializedName("category_group_name")
    var categoryGroupName: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("address_name")
    var addressName: String?,
    @SerializedName("road_address_name")
    var roadAddressName: String?,
    @SerializedName("x")
    var longitude: String?,
    @SerializedName("y")
    var latitude: String?,
    @SerializedName("place_url")
    var placeUrl: String?,
    @SerializedName("distance")
    var distance: String?,
)