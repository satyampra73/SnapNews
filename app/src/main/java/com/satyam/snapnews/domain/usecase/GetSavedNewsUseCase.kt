package com.satyam.snapnews.domain.usecase

import com.satyam.snapnews.data.model.Article
import com.satyam.snapnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val repository: NewsRepository) {
    fun execute(): Flow<List<Article>> {
        return repository.getSavedNews()
    }

}