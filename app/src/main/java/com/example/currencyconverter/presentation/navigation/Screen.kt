package com.example.currencyconverter.presentation.navigation

import com.example.currencyconverter.domain.UserInput

sealed class Screen(
    val route: String
) {

    data object Main : Screen(ROUTE_MAIN)

    data object Detail : Screen(ROUTE_DETAIL) {
        private const val ROUTE_FOR_ARGS = "detail"

        fun getRouteWithArgs(currency: UserInput): String {
            return "$ROUTE_FOR_ARGS/${currency.currentCurrency}/${currency.targetCurrency}/${currency.amount}"
        }
    }

    companion object {
        const val KEY_CURRENT_CURRENCY = "key_current_currency"
        const val KEY_TARGET_CURRENCY = "key_target_currency"
        const val KEY_AMOUNT = "key_amount"

        const val ROUTE_MAIN = "main"
        const val ROUTE_DETAIL = "detail/{$KEY_CURRENT_CURRENCY}/{$KEY_TARGET_CURRENCY}/{$KEY_AMOUNT}"
    }
}