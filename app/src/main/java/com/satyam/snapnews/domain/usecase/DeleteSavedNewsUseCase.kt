package com.satyam.snapnews.domain.usecase

import com.satyam.snapnews.data.model.Article
import com.satyam.snapnews.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}