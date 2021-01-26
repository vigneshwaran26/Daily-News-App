package com.example.newapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newapplication.R
import com.example.newapplication.database.ArticleDatabase
import com.example.newapplication.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {

    lateinit var viewModel : NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModalProviderFactory(application,newRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}