package com.ssafy.guseul.data.remote.datasource.place.model

import com.google.gson.annotations.SerializedName
import com.ssafy.guseul.data.remote.datasource.base.DataToDomainMapper
import com.ssafy.guseul.domain.entity.place.AddressEntity

data class AddressResponse(
    @SerializedName("meta")
    var meta: AddressMetaResponse?,
    @SerializedName("document")
    var document: AddressDocumentResponse?
) : DataToDomainMapper<AddressEntity> {
    override fun toDomainModel(): AddressEntity {
        //데이터를 아에 못받았다면
        if (document == null) {
            return AddressEntity("", "")
        }

        // 도로명이 없다면 주소로
        return AddressEntity(
            addressName = document?.roadAddress?.addressName ?: document?.address?.addressName,
            address3depthName = document?.roadAddress?.region3depthName
                ?: document?.address?.region3depthName
        )
    }
}

data class AddressMetaResponse(
    @SerializedName("total_count")
    var totalCount: Int?
)

data class AddressDocumentResponse(
    @SerializedName("address")
    var address: AddressOldAddressResponse?,
    @SerializedName("road_address")
    var roadAddress: AddressRoadAddressResponse?
)

data class AddressOldAddressResponse(
    @SerializedName("address_name")
    var addressName: String?,
    @SerializedName("region_1depth_name")
    var region1depthName: String?,
    @SerializedName("region_2depth_name")
    var region2depthName: String?,
    @SerializedName("region_3depth_name")
    var region3depthName: String?,
    @SerializedName("mountain_yn")
    var mountainYN: String?,
    @SerializedName("main_address_no")
    var mainAddressNo: String?,
    @SerializedName("sub_address_no")
    var subAddressNo: String?
)

data class AddressRoadAddressResponse(
    @SerializedName("address_name")
    var addressName: String?,
    @SerializedName("region_1depth_name")
    var region1depthName: String?,
    @SerializedName("region_2depth_name")
    var region2depthName: String?,
    @SerializedName("region_3depth_name")
    var region3depthName: String?,
    @SerializedName("road_name")
    var roadName: String?,
    @SerializedName("underground_yn")
    var undergroundYN: String?,
    @SerializedName("main_building_no")
    var mainBuildingNo: String?,
    @SerializedName("sub_building_no")
    var subBuildingNo: String?,
    @SerializedName("building_name")
    var buildingName: String?,
    @SerializedName("zone_no")
    var zoneNo: String?,
)