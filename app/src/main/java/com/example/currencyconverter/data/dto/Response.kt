package com.example.currencyconverter.data.dto

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("result") val result: String,
    @SerializedName("time_last_update_unix") val lastUpdateTime: String,
    @SerializedName("base_code") val baseCode: String,
    @SerializedName("conversion_rates") val conversionRates: Map<String, Double>
)
