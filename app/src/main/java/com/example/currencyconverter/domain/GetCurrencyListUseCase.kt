package com.example.currencyconverter.domain

import javax.inject.Inject

class GetCurrencyListUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(): DataState<List<String>> {
        return try {
            DataState.Success(repository.getCurrencyList())
        } catch (e: Exception) {
            DataState.Error(e.message.toString())
        }
    }
}