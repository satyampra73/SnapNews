package com.satyam.snapnews.domain.usecase

import com.satyam.snapnews.data.model.APIResponse
import com.satyam.snapnews.data.util.Resource
import com.satyam.snapnews.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery: String)  : Resource<APIResponse>{
        return newsRepository.getSearchedNews(searchQuery)
    }
}