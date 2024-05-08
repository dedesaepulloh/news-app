package com.dedesaepulloh.tribunnews.data.remote

import com.dedesaepulloh.tribunnews.data.remote.model.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("tnewsapi/rss/tesapi/tribunnews")
    suspend fun getNews(): Response<NewsResponse>
}