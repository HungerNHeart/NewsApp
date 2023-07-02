package com.example.newsapp.data.remote

import com.google.gson.annotations.SerializedName

class ArticleResponseDto : BaseDto<ArticleDto>()

data class ArticleDto(
    @SerializedName("_id")
    val id : String,
    @SerializedName("snippet")
    val snippet : String,
    @SerializedName("word_count")
    val wordCount : Int,
    @SerializedName("headline")
    val articleHeadline : ArticleHeadlineDto? = null,
    @SerializedName("pub_date")
    val publishedDate : String,
    @SerializedName("byline")
    val articleByLine : ArticleByLineDto? = null,
    @SerializedName("web_url")
    val webUrl : String
)

data class ArticleHeadlineDto(
    @SerializedName("main")
    val main : String,
    @SerializedName("kicker")
    val kicker : String
)

data class ArticleByLineDto(
    @SerializedName("original")
    val original : String
)

data class ArticleMultiMediaDto(
    @SerializedName("type")
    val type : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("height")
    val height : Int,
    @SerializedName("width")
    val width : Int,

)