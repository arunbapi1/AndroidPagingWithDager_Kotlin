package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.model.News
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.network.NetworkService
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(private val networkService: NetworkService,
        private val compositeDisposable: CompositeDisposable
        )
    : DataSource.Factory<Int, News>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}