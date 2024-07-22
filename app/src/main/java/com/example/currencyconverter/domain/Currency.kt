package com.example.currencyconverter.domain

data class Currency(
    val userInput: UserInput,
    val amountInTargetCurrency: Pair<Double, Double>,
    val lastTimeUpdate: String
)
