package com.example.newapplication.repository

import com.example.newapplication.api.Retrofitinstance
import com.example.newapplication.db.ArticleDatabase
import com.example.newapplication.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(CountryCode : String, PageNumber:Int) =
        Retrofitinstance.api.getBreakingNew(CountryCode,PageNumber)

    suspend fun searchNews(searchquery:String,PageNumber: Int) =
        Retrofitinstance.api.searcheverything(searchquery,PageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}