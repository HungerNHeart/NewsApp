package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
@TypeConverters(ArticleTypeConverter::class)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract val articleDao : ArticleDao
}