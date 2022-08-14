package com.link.newsfeedsapp.core.remote

import com.link.newsfeedsapp.core.models.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("articles?source=the-next-web&apiKey=533af958594143758318137469b41ba9")
    suspend fun getNextWebArticles(): Response<ArticlesResponse>

    @GET("articles?source=associated-press&apiKey=533af958594143758318137469b41ba9")
    suspend fun getAssociatedPressArticles(): Response<ArticlesResponse>
}