package com.satyam.snapnews.domain.usecase

import com.satyam.snapnews.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
}