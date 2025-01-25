package com.satyam.snapnews.data.repository.dataSourceImpl

import com.satyam.snapnews.data.api.NewsApiService
import com.satyam.snapnews.data.model.APIResponse
import com.satyam.snapnews.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService,
    private val country : String,
    private val page : Int
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(): Response<APIResponse> {
        return newsApiService.getTopHeadlines(country,page)
    }
}