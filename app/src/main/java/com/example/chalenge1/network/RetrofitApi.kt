package com.example.chalenge1.network

import com.example.chalenge1.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/api/m/v2/")
    fun getRequest():Response<List<NewsModel>>

}