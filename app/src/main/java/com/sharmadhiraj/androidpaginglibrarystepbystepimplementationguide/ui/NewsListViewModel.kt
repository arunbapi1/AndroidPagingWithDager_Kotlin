package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.base.BaseViewModel
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.repository.NewsDataSource
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.repository.NewsDataSourceFactory
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.util.State
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.model.News
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.network.NetworkService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsListViewModel : BaseViewModel() {

   // private val networkService = NetworkService.getService()
   @Inject
   lateinit var networkService: NetworkService
    var newsList: LiveData<PagedList<News>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val newsDataSourceFactory: NewsDataSourceFactory

    init {
        newsDataSourceFactory = NewsDataSourceFactory(networkService, compositeDisposable)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        newsList = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<NewsDataSource,
            State>(newsDataSourceFactory.newsDataSourceLiveData, NewsDataSource::state)

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}