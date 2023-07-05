package com.example.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "Articles"
)
data class ArticleEntity(
    @PrimaryKey
    val id : String,
    val snippet : String,
    val wordCount : Int,
    val publishedDate : String,
    val webUrl : String,
    val byLine : String? = null,
    val headLine : String? = null,
    val kicker : String? = null,
    val media : List<MultiMediaEntity>? = null
)

data class MultiMediaEntity(
    val type : String,
    val url : String,
    val height : Int,
    val width : Int
)
