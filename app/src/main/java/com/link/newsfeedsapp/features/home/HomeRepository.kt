package com.link.newsfeedsapp.features.home

import com.link.newsfeedsapp.core.models.ArticlesResponse
import com.link.newsfeedsapp.core.remote.Api
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: Api) {

    suspend fun getAssociatedPressArticles(): Response<ArticlesResponse> {
        return api.getAssociatedPressArticles()
    }

    suspend fun getNextWebArticles(): Response<ArticlesResponse> {
        return api.getNextWebArticles()
    }

}