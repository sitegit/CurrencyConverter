package com.example.currencyconverter.di

import com.example.currencyconverter.data.CurrencyRepositoryImpl
import com.example.currencyconverter.domain.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.Calendar
import java.util.Locale

@Module
interface DataModule {

    @AppScope
    @Binds
    fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    companion object {

        @AppScope
        @Provides
        fun provideCalendar(): Calendar = Calendar.getInstance()

        @AppScope
        @Provides
        fun provideLocale(): Locale = Locale("ru")
    }
}