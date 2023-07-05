package com.example.newsapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArticleTypeConverter {

    @TypeConverter
    fun fromMedia(list : List<MultiMediaEntity>?): String {
        val type = object: TypeToken<List<MultiMediaEntity>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toMedia(value: String): List<MultiMediaEntity>? {
        val type = object : TypeToken<List<MultiMediaEntity>>() {}.type
        return Gson().fromJson(value, type)
    }
}