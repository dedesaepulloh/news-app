package com.dedesaepulloh.tribunnews.repository

import com.dedesaepulloh.tribunnews.data.remote.ApiService
import com.dedesaepulloh.tribunnews.data.remote.model.response.NewsItem
import com.dedesaepulloh.tribunnews.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val api: ApiService,
) {
    suspend fun getNews(): Resource<List<NewsItem>> {
        return try {
            val response = api.getNews()
            if (response.isSuccessful) {
                Resource.Success(response.body()?.data ?: emptyList())
            } else {
                Resource.Error(Exception("Failed to get data"))
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}