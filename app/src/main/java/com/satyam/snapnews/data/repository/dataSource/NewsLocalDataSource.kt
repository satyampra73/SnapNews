package com.satyam.snapnews.data.repository.dataSource

import com.satyam.snapnews.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
}