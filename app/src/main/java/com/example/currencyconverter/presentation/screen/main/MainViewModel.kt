package com.example.currencyconverter.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.DataState
import com.example.currencyconverter.domain.GetCurrencyListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCurrencyListUseCase: GetCurrencyListUseCase
) : ViewModel() {

    private val _listCurrency = MutableStateFlow<DataState<List<String>>>(DataState.Initial)
    val listCurrency = _listCurrency.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _listCurrency.value = DataState.Loading
            _listCurrency.value = getCurrencyListUseCase()
        }
    }
}