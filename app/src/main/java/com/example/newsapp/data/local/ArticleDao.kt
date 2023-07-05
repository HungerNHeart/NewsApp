package com.example.newsapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles : ArrayList<ArticleEntity>)

    @Query("SELECT * FROM Articles")
    fun getAllArticles() : PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM Articles")
    fun clearAllArticles()

}