package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.di

import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.repository.NewsDataSourceFactory
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.ui.NewsListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param newsDataSource PostListViewModel in which to inject the dependencies
     */
    fun inject(newsListViewModel: NewsListViewModel)

    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param newsDataSourceFactory PostViewModel in which to inject the dependencies
     */
//    fun inject(newsDataSourceFactory: NewsDataSourceFactory)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
        //fun dataSourceFactoryModule(newsDataSourceFactory: NewsDataSourceFactory): Builder
    }
}