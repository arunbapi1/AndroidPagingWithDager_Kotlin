package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.base

import androidx.lifecycle.ViewModel
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.di.DaggerViewModelInjector
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.di.NetworkModule
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.di.ViewModelInjector
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.ui.NewsListViewModel


abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            // is NewsDataSourceFactory -> injector.inject(this)
            is NewsListViewModel -> injector.inject(this)
        }
    }
}