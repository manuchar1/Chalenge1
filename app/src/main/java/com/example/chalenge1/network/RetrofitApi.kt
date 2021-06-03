package com.example.chalenge1.network

import com.example.chalenge1.models.Constants
import com.example.chalenge1.models.NewsModel
import com.example.chalenge1.models.PaginatedData
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET(Constants.API_ENDPOINT)
    fun getRequest(): Response<List<NewsModel>>

}