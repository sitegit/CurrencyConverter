package com.example.currencyconverter.data

import com.example.currencyconverter.domain.Currency
import com.example.currencyconverter.domain.CurrencyRepository
import com.example.currencyconverter.domain.UserInput
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val calendar: Calendar,
    private val locale: Locale
) : CurrencyRepository {
    private var cachedCurrencyList: List<String>? = null

    override suspend fun getData(currency: UserInput): Currency {
        val result: Response = apiService.getData(currency.currentCurrency)
        val lastTimeUpdate: String = formatTimestamp(result.lastUpdateTime)
        val conversionRate: Double? = result.conversionRates[currency.targetCurrency]
        val amountInTargetCurrency: Pair<Double, Double> = conversionRate?.let {
            convertCurrency(1.0, it) to convertCurrency(currency.amount.toDouble(), it)
        } ?: throw RuntimeException("Conversion rate not found for ${currency.targetCurrency}")

        return Currency(currency, amountInTargetCurrency, lastTimeUpdate)
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
        val timeFormat = SimpleDateFormat("HH:mm", locale)
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", locale)
        calendar.timeInMillis = timestamp * 1000

        return "${timeFormat.format(calendar.time)}, ${dateFormat.format(calendar.time)}"
    }
}