package com.satyam.snapnews.presentation.di

import com.satyam.snapnews.data.repository.NewsRepositoryImpl
import com.satyam.snapnews.data.repository.dataSource.NewsLocalDataSource
import com.satyam.snapnews.data.repository.dataSource.NewsRemoteDataSource
import com.satyam.snapnews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource,newsLocalDataSource)
    }

}