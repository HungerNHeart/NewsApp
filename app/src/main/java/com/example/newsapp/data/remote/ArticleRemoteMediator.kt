package com.example.newsapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.local.NewsAppDatabase
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
    ): MediatorResult  = try {
        val loadKey = when(loadType){
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )
            LoadType.APPEND -> {
                state.anchorPosition?.let {
                    state.pages.size + 1
                }
            }
        }
    } catch(e: IOException) {
        MediatorResult.Error(e)
    } catch(e: HttpException) {
        MediatorResult.Error(e)
    }


}