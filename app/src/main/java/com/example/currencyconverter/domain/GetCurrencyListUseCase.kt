package com.example.currencyconverter.domain

import javax.inject.Inject

class GetCurrencyListUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(): List<String> {
        return repository.getCurrencyList()
    }
}