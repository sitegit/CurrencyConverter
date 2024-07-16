package com.example.currencyconverter.data.remote

import com.example.currencyconverter.BuildConfig
import com.example.currencyconverter.data.dto.Response
import retrofit2.http.GET

interface ApiService {

    @GET("latest/USD")
    suspend fun getData(): Response
}