package com.example.newsapp.data.remote

open class BaseDto<T>{
    val status: String? = null
    val copyright: String? = null
    val response : ResponseDto<T>? = null
}

open class ResponseDto<T>{
    var meta : ResponseMetaDto? = null
    val docs : List<T>? = null
}

data class ResponseMetaDto(
    val hits : Long,
    val offset : Int,
    val time : Int
)