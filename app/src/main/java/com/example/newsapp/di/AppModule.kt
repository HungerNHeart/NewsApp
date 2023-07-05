package com.example.newsapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.local.NewsAppDatabase
import com.example.newsapp.data.remote.ArticleRemoteMediator
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.NewsApi.Companion.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNewDatabase(@ApplicationContext context : Context) : NewsAppDatabase = Room.databaseBuilder(
        context,
        NewsAppDatabase::class.java,
        "news_data_base.db"
    ).build()

    @Provides
    @Singleton
    fun providesArticleApi() : NewsApi = Retrofit
        .Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)

    @Provides
    @Singleton
    fun providesArticlePager(
        db: NewsAppDatabase,
        newsApi: NewsApi
    ): Pager<Int, ArticleEntity> = Pager(
        config = PagingConfig(pageSize = 10),
        remoteMediator = ArticleRemoteMediator(
            db = db,
            newsApi = newsApi
        ),
        pagingSourceFactory = {
            db.articleDao.getAllArticles()
        }
    )

}