package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract val articleDao : ArticleDao
}