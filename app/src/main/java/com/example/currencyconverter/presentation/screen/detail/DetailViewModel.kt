package com.example.currencyconverter.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.Currency
import com.example.currencyconverter.domain.DataState
import com.example.currencyconverter.domain.GetCurrencyDetailUseCase
import com.example.currencyconverter.domain.UserInput
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    private val getCurrencyDetailUseCase: GetCurrencyDetailUseCase,
    @Assisted private val currency: UserInput
) : ViewModel() {

    private val _currencyData = MutableStateFlow<DataState<Currency>>(DataState.Initial)
    val currencyData = _currencyData.asStateFlow()

    init {
        loadDetailData()
    }

    fun loadDetailData() {
        viewModelScope.launch {
            _currencyData.value = DataState.Loading
            val result = getCurrencyDetailUseCase(currency)
            _currencyData.value = result
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted currency: UserInput,
        ): DetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            currency: UserInput
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(currency) as T
            }
        }
    }
}