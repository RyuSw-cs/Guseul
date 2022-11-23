package com.ssafy.guseul.data.remote.datasource.user.model

import com.google.gson.annotations.SerializedName
import com.kakao.sdk.user.model.User
import com.ssafy.guseul.data.remote.datasource.base.DataToDomainMapper
import com.ssafy.guseul.domain.entity.user.UserEntity

data class UserResponse(
    @SerializedName("statusCode")
    var statusCode : Int?,
    @SerializedName("resMessage")
    var resMessage : String?,
    @SerializedName("data")
    var user : UserBasicResponse?
) : DataToDomainMapper<UserEntity>{
    override fun toDomainModel(): UserEntity {
        return UserEntity(
            userId = user?.id ?: -1,
            nickname = user?.nickname ?: "",
            address = user?.address ?: ""
        )
    }
}