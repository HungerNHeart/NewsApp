package com.example.newsapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ArticleDao {

    @Upsert
    suspend fun upsetAll(articles : List<ArticleEntity>)

    @Query("SELECT * FROM Articles")
    suspend fun getAllArticles() : PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM Articles")
    suspend fun clearAllArticles()

}