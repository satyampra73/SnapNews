package com.satyam.snapnews.data.repository.dataSourceImpl

import com.satyam.snapnews.data.db.ArticleDao
import com.satyam.snapnews.data.model.Article
import com.satyam.snapnews.data.repository.dataSource.NewsLocalDataSource

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        return articleDao.insert(article)
    }
}