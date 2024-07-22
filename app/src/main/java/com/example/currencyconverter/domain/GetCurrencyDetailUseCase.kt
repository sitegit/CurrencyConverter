package com.example.currencyconverter.domain

import javax.inject.Inject

class GetCurrencyDetailUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(currency: Currency): CurrencyEntity {
        return repository.getData(currency)
    }
}