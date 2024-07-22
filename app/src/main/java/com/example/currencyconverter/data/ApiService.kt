package com.example.currencyconverter.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("latest/{base_code}")
    suspend fun getData(
        @Path("base_code") baseCode: String = "RUB"
    ): Response
}