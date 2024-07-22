package com.example.currencyconverter.di

import android.content.Context
import com.example.currencyconverter.presentation.core.ViewModelFactory
import com.example.currencyconverter.presentation.screen.detail.DetailViewModel
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun getViewModelFactory(): ViewModelFactory

    fun detailViewModelFactory(): DetailViewModel.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}