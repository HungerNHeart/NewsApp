package com.example.newsapp.view

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.map
import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.mapper.toArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    pager: Pager<Int, ArticleEntity>
) : ViewModel() {

    val allArticles = pager.flow.map {
        it.map {
            it.toArticle()
        }
    }

}