package com.satyam.snapnews.data.repository.dataSourceImpl

import android.R.attr.country
import com.satyam.snapnews.data.api.NewsApiService
import com.satyam.snapnews.data.model.APIResponse
import com.satyam.snapnews.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService,

) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country : String, page : Int): Response<APIResponse> {
        return newsApiService.getTopHeadlines(country,page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsApiService.getSearchedNews(country,searchQuery,page)
    }
}