package com.example.newsapp.data.mapper

import com.example.newsapp.data.local.ArticleEntity
import com.example.newsapp.data.local.MultiMediaEntity
import com.example.newsapp.data.remote.ArticleDto
import com.example.newsapp.domain.Article
import com.example.newsapp.domain.MultiMedia

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

fun ArticleDto.toArticleEntity() = ArticleEntity(
    id = this.id,
    snippet = this.snippet,
    wordCount = this.wordCount,
    publishedDate = this.publishedDate,
    webUrl = this.webUrl,
    byLine = this.articleByLine?.original,
    headLine = this.articleHeadline?.main.orEmpty(),
    kicker = this.articleHeadline?.kicker,
    media = this.multimedia?.map {
        MultiMediaEntity(
            type = it.type,
            url = it.url,
            height = it.height,
            width = it.width
        )
    }
)

fun ArticleEntity.toArticle() = Article(
    id = this.id,
    snippet = this.snippet,
    wordCount = this.wordCount,
    publishedDate = this.publishedDate,
    webUrl = this.webUrl,
    byLine = byLine,
    headLine = headLine,
    kicker = kicker,
    multiMedia = this.media?.map { it.toMultiMedia() }
)

fun MultiMediaEntity.toMultiMedia() = MultiMedia(
    type = this.type,
    url = this.url,
    height = this.height,
    width = this.width
)