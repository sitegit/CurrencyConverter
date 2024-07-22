package com.example.currencyconverter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.domain.Currency
import com.example.currencyconverter.presentation.screen.detail.DetailViewModel
import com.example.currencyconverter.presentation.screen.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    companion object {
        @Provides
        fun provideDetailViewModelFactory(
            assistedFactory: DetailViewModel.Factory
        ): ViewModelProvider.Factory {
            return DetailViewModel.provideFactory(assistedFactory, Currency())
        }
    }
}