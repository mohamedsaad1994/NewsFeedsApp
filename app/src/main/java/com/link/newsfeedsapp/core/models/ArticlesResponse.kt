package com.link.newsfeedsapp.core.models

data class ArticlesResponse(
    val articles: List<Article>,
    val sortBy: String,
    val source: String,
    val status: String
)