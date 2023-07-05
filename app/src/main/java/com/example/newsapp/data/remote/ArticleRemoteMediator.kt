package com.example.newsapp.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.local.NewsAppDatabase
import com.example.newsapp.data.mapper.toArticleEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator @Inject constructor(
    private val db : NewsAppDatabase,
    private val newsApi: NewsApi,
) : RemoteMediator<Int, ArticleEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {
            Log.d("RemoteMediator", "state.pages : ${state.pages.size} | $loadType")
            val loadKey: Int = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        0
                    } else {
                        state.pages.size + 1
                    }
                }
            }
            val articles = newsApi.getArticles(
                page = loadKey
            )
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.articleDao.clearAllArticles()
                }
                articles.response?.docs?.let {
                    db.articleDao.insertAll(articles = it.map { it.toArticleEntity() }
                        .toCollection(ArrayList()))
                }

            }
            MediatorResult.Success(
                endOfPaginationReached = articles.response?.docs?.isEmpty() ?: false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


}