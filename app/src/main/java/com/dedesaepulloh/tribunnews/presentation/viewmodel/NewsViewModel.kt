package com.dedesaepulloh.tribunnews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedesaepulloh.tribunnews.data.remote.model.response.NewsItem
import com.dedesaepulloh.tribunnews.domain.usecase.NewsUseCase
import com.dedesaepulloh.tribunnews.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: NewsUseCase) : ViewModel() {

    private val _news = MutableStateFlow<Resource<List<NewsItem>>>(Resource.Loading)
    val news: StateFlow<Resource<List<NewsItem>>> = _news

    fun loadNews() {
        viewModelScope.launch {
            _news.value = useCase.getNews()
        }
    }

}