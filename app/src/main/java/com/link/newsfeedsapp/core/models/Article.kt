package com.link.newsfeedsapp.core.models

data class Article(
    val author: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)