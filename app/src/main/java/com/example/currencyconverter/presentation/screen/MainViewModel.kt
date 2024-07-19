package com.example.currencyconverter.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.remote.ApiFactory
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api = ApiFactory.apiService
    val listCurrency = mutableListOf<String>()

    fun getData(baseCurrency: String = "RUB") {
        viewModelScope.launch {
            val result = api.getData(baseCurrency)

            if (listCurrency.isEmpty()) listCurrency.addAll(result.conversionRates.keys)
        }
    }
}