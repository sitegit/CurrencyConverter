package com.example.currencyconverter.domain

data class CurrencyEntity(
    val userInput: Currency,
    val amountInTargetCurrency: Pair<Double, Double>,
    val lastTimeUpdate: String
)
