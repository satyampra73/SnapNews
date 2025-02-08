package com.satyam.snapnews.presentation.di

import android.app.Application
import com.satyam.snapnews.domain.usecase.GetNewsHeadlinesUseCase
import com.satyam.snapnews.domain.usecase.GetSearchedNewsUseCase
import com.satyam.snapnews.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase

    ):NewsViewModelFactory{
        return NewsViewModelFactory(application,getNewsHeadlinesUseCase,getSearchedNewsUseCase)
    }
}