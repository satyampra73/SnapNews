package com.satyam.snapnews.data.repository.dataSource

import com.satyam.snapnews.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(): Response<APIResponse>
}