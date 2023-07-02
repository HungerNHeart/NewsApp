package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.NewsAppDatabase
import com.example.newsapp.data.remote.NewsApi.Companion.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNewDatabase(@ApplicationContext context : Context) : NewsAppDatabase
    = Room.databaseBuilder(
        context,
        NewsAppDatabase::class.java,
        "news_data_base.db"
    ).build()

    @Provides
    @Singleton
    fun providesArticleApi()
    = Retrofit
        .Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())

}