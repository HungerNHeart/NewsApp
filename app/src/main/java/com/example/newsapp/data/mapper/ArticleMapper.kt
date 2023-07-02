package com.example.newsapp.data.mapper

import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.remote.ArticleDto
import com.example.newsapp.domain.Article

fun ArticleDto.toArticle() = Article(
    id = this.id,
    snippet = this.snippet,
    wordCount = this.wordCount,
    publishedDate = this.publishedDate,
    webUrl = this.webUrl,
    byLine = this.articleByLine?.original,
    headLine = this.articleHeadline?.main.orEmpty(),
    kicker = this.articleHeadline?.kicker
)

fun ArticleEntity.toArticle() = Article(
    id = this._id,
    snippet = this.snippet,
    wordCount = this.wordCount,
    publishedDate = this.publishedDate,
    webUrl = this.webUrl,
    byLine = byLine,
    headLine = headLine,
    kicker = kicker
)