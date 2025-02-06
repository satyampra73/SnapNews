package com.satyam.snapnews.presentation.di

import com.satyam.snapnews.data.api.NewsApiService
import com.satyam.snapnews.data.repository.dataSource.NewsRemoteDataSource
import com.satyam.snapnews.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApiService)

    }
}