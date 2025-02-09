package com.satyam.snapnews.data.repository.dataSourceImpl

import com.satyam.snapnews.data.db.ArticleDao
import com.satyam.snapnews.data.model.Article
import com.satyam.snapnews.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        return articleDao.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    override suspend fun deleteArticlesFromDB(article: Article) {
        return articleDao.deleteArticle(article)
    }
}