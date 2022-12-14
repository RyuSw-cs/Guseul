package com.ssafy.guseul.data.remote.datasource.place.model

import com.google.gson.annotations.SerializedName
import com.ssafy.guseul.data.remote.datasource.base.DataToDomainMapper
import com.ssafy.guseul.domain.entity.place.PlaceEntity

data class PlaceResponse(
    @SerializedName("meta")
    var meta: PlaceMetaResponse?,
    @SerializedName("documents")
    var documents: List<PlaceDocumentResponse>?
) : DataToDomainMapper<List<PlaceEntity>> {
    override fun toDomainModel(): List<PlaceEntity> {

        fun filterCategory(categoryCode: String): String {
            return when (categoryCode) {
                "MT1" -> "대형마트"
                "CS2" -> "편의점"
                "MT" -> "대형마트"
                "FD6" -> "음식점"
                "HP8" -> "병원"
                else -> ""
            }
        }

        return documents?.map {
            PlaceEntity(
                it.placeName ?: "",
                it.phone,
                it.roadAddressName ?: "",
                filterCategory(it.categoryGroupCode ?: ""),
                it.longitude?.toDouble() ?: 0.0,
                it.latitude?.toDouble() ?: 0.0
            )
        } ?: emptyList()
    }
}

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