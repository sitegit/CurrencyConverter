package com.example.currencyconverter

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.currencyconverter.di.AppComponent
import com.example.currencyconverter.di.DaggerAppComponent

class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): AppComponent {
    return (LocalContext.current.applicationContext as App).component
}