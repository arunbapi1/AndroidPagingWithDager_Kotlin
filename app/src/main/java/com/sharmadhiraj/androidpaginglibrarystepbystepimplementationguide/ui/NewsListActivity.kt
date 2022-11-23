package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.R
import com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.ui.NewsListFragment.Companion.newInstance

class NewsListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        val imageFragment = newInstance()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, imageFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}