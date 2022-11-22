package com.ssafy.guseul.data.remote.datasource.board

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BoardResponse(

    @Expose
    @SerializedName("userId")
    val userId: Int,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("content")
    val content: String,
    @Expose
    @SerializedName("category")
    val category: Int,
    @Expose
    @SerializedName("departures")
    val departures: String,
    @Expose
    @SerializedName("arrivals")
    val arrivals: String,
    @Expose
    @SerializedName("headCount")
    val headCount: Int,
    @Expose
    @SerializedName("time")
    val time: String,
    @Expose
    @SerializedName("openChattingUrl")
    val openChattingUrl: String,
    @Expose
    @SerializedName("productUrl")
    val productUrl: String,
    @Expose
    @SerializedName("location")
    val location: String,
    @Expose
    @SerializedName("product")
    val product: String,
    @Expose
    @SerializedName("price")
    val price: Int,
    @Expose
    @SerializedName("end")
    val end: Boolean
)
