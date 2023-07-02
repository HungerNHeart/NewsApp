package com.example.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Articles"
)
data class ArticleEntity(
    @PrimaryKey
    val id : Int,
    val snippet : String,
    val wordCount : Int,
    val publishedDate : String,
    val webUrl : String,
    val byLine : String? = null,
    val headLine : String? = null,
    val kicker : String? = null
)
