package com.example.currencyconverter.domain

interface CurrencyRepository {

    suspend fun getData(currency: UserInput): Currency

    suspend fun getCurrencyList(): List<String>
}