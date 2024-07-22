package com.example.currencyconverter.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.Currency
import com.example.currencyconverter.domain.CurrencyEntity
import com.example.currencyconverter.domain.GetCurrencyDetailUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    private val getCurrencyDetailUseCase: GetCurrencyDetailUseCase,
    @Assisted("currency") private val currency: Currency
) : ViewModel() {

    private val _currencyData = MutableStateFlow(
        CurrencyEntity(
            userInput = Currency(),
            amountInTargetCurrency = 0.0 to 0.0,
            lastTimeUpdate = ""
        )
    )
    val currencyData = _currencyData.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getCurrencyDetailUseCase(currency)
            _currencyData.value = result
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("currency") currency: Currency,
        ): DetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            currency: Currency
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(currency) as T
            }
        }
    }
}