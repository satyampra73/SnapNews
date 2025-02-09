package com.satyam.snapnews.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.satyam.snapnews.domain.usecase.DeleteSavedNewsUseCase
import com.satyam.snapnews.domain.usecase.GetNewsHeadlinesUseCase
import com.satyam.snapnews.domain.usecase.GetSavedNewsUseCase
import com.satyam.snapnews.domain.usecase.GetSearchedNewsUseCase
import com.satyam.snapnews.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val app: Application,
    val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    val savedNewsUseCase: SaveNewsUseCase,
    val getSavedNewsUseCase: GetSavedNewsUseCase,
    val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase,
            savedNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedNewsUseCase
        ) as T
    }


}