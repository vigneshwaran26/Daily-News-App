package com.example.newapplication.api

import com.example.newapplication.models.NewsResponse
import com.example.newapplication.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNew(

        @Query("country")
        CountryCode : String ="US",
        @Query("page")
        PageNumber : Int =  1,
        @Query("apikey")
        apikey : String = API_KEY
    ) :Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searcheverything(
        @Query("q")
        searchQuery : String,
        @Query("page")
        PageNumber : Int =  1,
        @Query("apikey")
        apikey : String = API_KEY
    ) :Response<NewsResponse>

}