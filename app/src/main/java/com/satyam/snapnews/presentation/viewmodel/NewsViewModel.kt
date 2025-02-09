package com.satyam.snapnews.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.satyam.snapnews.data.model.APIResponse
import com.satyam.snapnews.data.model.Article
import com.satyam.snapnews.data.util.Resource
import com.satyam.snapnews.domain.usecase.GetNewsHeadlinesUseCase
import com.satyam.snapnews.domain.usecase.GetSavedNewsUseCase
import com.satyam.snapnews.domain.usecase.GetSearchedNewsUseCase
import com.satyam.snapnews.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    val app: Application,
    val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    val savedNewsUseCase: SaveNewsUseCase,
    val getSavedNewsUseCase: GetSavedNewsUseCase,

    ) : AndroidViewModel(app) {
    val newsHeadLines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {

        newsHeadLines.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadLines.postValue(apiResult)
            } else {
                newsHeadLines.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }


    }


    fun getSearchedNews(country: String, searchQuery: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO) {

            searchedNews.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = getSearchedNewsUseCase.execute(country, searchQuery, page)
                    searchedNews.postValue(apiResult)
                } else {
                    searchedNews.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                searchedNews.postValue(Resource.Error(e.message.toString()))
            }


        }

    //local Data

    fun saveArticle(article: Article) = viewModelScope.launch {
        savedNewsUseCase.execute(article)
    }


    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect{
            emit(it)
        }
    }



    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return result
    }
}