package com.example.currencyconverter.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val currentCurrency: String = "RUB",
    val targetCurrency: String = "USD",
    val amount: String = "0.0"
) : Parcelable
