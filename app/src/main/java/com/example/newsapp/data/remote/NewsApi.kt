package com.example.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("articlesearch.json")
    suspend fun getArticles(
        @Query("page") page : Int,
        @Query("api-key") apiKey : String = "pf6FgeMTQXi38BAFb9voVvHtrEQlwUlp"
    ) : ArticleResponseDto


    companion object {
        const val BaseUrl = "https://api.nytimes.com/svc/search/v2/"
    }
}