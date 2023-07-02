package com.example.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("articlesearch.json")
    suspend fun getArticles(
        @Query("page") page : Int
    ) : ArticleResponseDto


    companion object {
        const val BaseUrl = "https://api.nytimes.com/svc/search/v2/"
    }
}