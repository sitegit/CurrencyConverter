package com.example.currencyconverter.domain

interface CurrencyRepository {

    suspend fun getData(currency: Currency): CurrencyEntity

    suspend fun getCurrencyList(): List<String>
}