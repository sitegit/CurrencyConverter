package com.example.currencyconverter.domain

import javax.inject.Inject

class GetCurrencyDetailUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(currency: UserInput): DataState<Currency> {
        return try {
            DataState.Success(repository.getData(currency))
        } catch (e: Exception) {
            DataState.Error(e.message.toString())
        }
    }
}