package com.satyam.snapnews.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.satyam.snapnews.domain.usecase.GetNewsHeadlinesUseCase
import com.satyam.snapnews.domain.usecase.GetSearchedNewsUseCase

class NewsViewModelFactory(
    private val app: Application,
    val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase
        ) as T
    }


}