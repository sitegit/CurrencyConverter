package com.example.currencyconverter.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.currencyconverter.domain.UserInput
import com.example.currencyconverter.presentation.navigation.Screen.Companion.KEY_AMOUNT
import com.example.currencyconverter.presentation.navigation.Screen.Companion.KEY_CURRENT_CURRENCY
import com.example.currencyconverter.presentation.navigation.Screen.Companion.KEY_TARGET_CURRENCY

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    mainScreenContent: @Composable () -> Unit,
    detailScreenContent: @Composable (UserInput) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Main.route,
    ) {

        composable(Screen.Main.route) {
            mainScreenContent()
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(KEY_CURRENT_CURRENCY) { type = NavType.StringType },
                navArgument(KEY_TARGET_CURRENCY) { type = NavType.StringType },
                navArgument(KEY_AMOUNT) { type = NavType.StringType }
            )
        ) {backStackEntry ->
            val currentCurrency = backStackEntry.arguments?.getString(KEY_CURRENT_CURRENCY) ?: ""
            val targetCurrency = backStackEntry.arguments?.getString(KEY_TARGET_CURRENCY) ?: ""
            val amount = backStackEntry.arguments?.getString(KEY_AMOUNT) ?: ""
            val currency = UserInput(currentCurrency, targetCurrency, amount)
            detailScreenContent(currency)
        }
    }
}