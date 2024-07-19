package com.example.currencyconverter.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.presentation.navigation.AppNavGraph
import com.example.currencyconverter.presentation.navigation.Screen
import com.example.currencyconverter.presentation.screen.DetailScreen
import com.example.currencyconverter.presentation.screen.MainScreen
import com.example.currencyconverter.presentation.theme.CurrencyConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CurrencyConverterTheme {
                val navHostController = rememberNavController()

                AppNavGraph(
                    navHostController = navHostController,
                    mainScreenContent = {
                        MainScreen(
                            onClickNextScreen = {
                                navHostController.navigate(Screen.Detail.getRouteWithArgs(it))
                            }
                        )
                    },
                    detailScreenContent = {
                        DetailScreen(
                            currency = it
                        ) {
                            navHostController.popBackStack(route = Screen.Main.route, inclusive = false)
                        }
                    }
                )
            }
        }
    }
}
