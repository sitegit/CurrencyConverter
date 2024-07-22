package com.example.currencyconverter.di

import com.example.currencyconverter.data.remote.ApiFactory
import com.example.currencyconverter.data.remote.ApiService
import com.example.currencyconverter.data.repository.CurrencyRepositoryImpl
import com.example.currencyconverter.domain.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @AppScope
    @Binds
    fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    companion object {

        @AppScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}