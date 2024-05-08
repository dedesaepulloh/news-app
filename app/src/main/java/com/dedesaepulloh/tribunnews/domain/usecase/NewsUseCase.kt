package com.dedesaepulloh.tribunnews.domain.usecase

import com.dedesaepulloh.tribunnews.data.remote.model.response.NewsItem
import com.dedesaepulloh.tribunnews.repository.NewsRepository
import com.dedesaepulloh.tribunnews.utils.Resource
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend fun getNews(): Resource<List<NewsItem>> {
        return repository.getNews()
    }
}