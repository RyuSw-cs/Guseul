package com.ssafy.guseul.domain.entity.user

data class BoardEntity (
    val userId: Int,
    val title: String,
    val content: String,
    val category: Int,
    val departures: String? = "",
    val arrivals: String? = "",
    val headCount: Int? = 0,
    val time: String? = "",
    val openChattingUrl: String? = "",
    val productUrl: String? = "",
    val location: String? = "",
    val product: String? = "",
    val price: Int? = 0,
    val state: Boolean? = false
)