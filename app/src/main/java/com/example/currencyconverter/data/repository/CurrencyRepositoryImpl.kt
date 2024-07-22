package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.remote.ApiService
import com.example.currencyconverter.domain.Currency
import com.example.currencyconverter.domain.CurrencyEntity
import com.example.currencyconverter.domain.CurrencyRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CurrencyRepository {
    private var cachedCurrencyList: List<String>? = null

    override suspend fun getData(currency: Currency): CurrencyEntity{
        val result = apiService.getData(currency.currentCurrency)
        val lastTimeUpdate = formatTimestamp(result.lastUpdateTime)
        val conversionRate = result.conversionRates[currency.targetCurrency]
        val amountInTargetCurrency: Pair<Double, Double> = conversionRate?.let {
            convertCurrency(1.0, it) to convertCurrency(currency.amount.toDouble(), it)
        } ?: throw IllegalArgumentException("Conversion rate not found for ${currency.targetCurrency}")

        return CurrencyEntity(
            currency,
            amountInTargetCurrency,
            lastTimeUpdate
        )
    }

    override suspend fun getCurrencyList(): List<String> {
        return cachedCurrencyList ?: apiService.getData().conversionRates.keys.toList().also {
            cachedCurrencyList = it
        }
    }

    private fun convertCurrency(amount: Double, rate: Double): Double {
        return amount * rate
    }

    private fun formatTimestamp(timestamp: Long): String {
        val locale = Locale("ru")
        val calendar = Calendar.getInstance()

        calendar.timeInMillis = timestamp * 1000

        val timeFormat = SimpleDateFormat("HH:mm", locale)
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", locale)
        val time = timeFormat.format(calendar.time)
        val dateStr = dateFormat.format(calendar.time)

        return "Обновлено: $time, $dateStr"
    }
}