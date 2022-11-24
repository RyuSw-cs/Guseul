package com.ssafy.guseul.domain.entity.board

data class BoardEntity (
    val postId: Int = -1,
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
    val end: Boolean = false
)

data class IndivisualBoardEntity(
    val entity: BoardEntity,
    val isMine: Boolean
)