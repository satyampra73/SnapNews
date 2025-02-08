package com.satyam.snapnews.domain.usecase

import com.satyam.snapnews.data.model.APIResponse
import com.satyam.snapnews.data.util.Resource
import com.satyam.snapnews.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String,searchQuery: String,page: Int)  : Resource<APIResponse>{
        return newsRepository.getSearchedNews(country,searchQuery,page)
    }
}