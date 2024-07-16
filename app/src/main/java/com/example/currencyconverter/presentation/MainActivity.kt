package com.example.currencyconverter.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.currencyconverter.data.remote.ApiFactory
import com.example.currencyconverter.presentation.theme.CurrencyConverterTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConverterTheme {
                val api = ApiFactory.apiService
                LaunchedEffect(key1 = Unit) {
                    Log.i("MyTag", api.getData().conversionRates.toString())
                }
            }
        }
    }
}
