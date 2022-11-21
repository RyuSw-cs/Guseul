package com.ssafy.guseul.data.remote.datasource.base

interface DataToDomainMapper<T> {
    fun toDomainModel() : T
}