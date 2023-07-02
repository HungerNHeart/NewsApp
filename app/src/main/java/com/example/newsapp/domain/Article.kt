package com.example.newsapp.domain

data class Article(
    val id : String,
    val snippet : String,
    val wordCount : Int,
    val publishedDate : String,
    val webUrl : String,
    val byLine : String? = null,
    val headLine : String? = null,
    val kicker : String? = null
)
