package com.satyam.snapnews.presentation.di

import com.satyam.snapnews.data.db.ArticleDao
import com.satyam.snapnews.data.repository.dataSource.NewsLocalDataSource
import com.satyam.snapnews.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(articleDao: ArticleDao): NewsLocalDataSource {
      return NewsLocalDataSourceImpl(articleDao)
    }

}