package com.example.currencyconverter.presentation.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.presentation.navigation.AppNavGraph
import com.example.currencyconverter.presentation.navigation.Screen
import com.example.currencyconverter.presentation.screen.detail.DetailScreen
import com.example.currencyconverter.presentation.screen.main.MainScreen
import com.example.currencyconverter.presentation.theme.CurrencyConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CurrencyConverterTheme {
                val navHostController = rememberNavController()

                SetupNavGraph(
                    navHostController = navHostController,
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                        .padding(all = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun SetupNavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    AppNavGraph(
        navHostController = navHostController,
        mainScreenContent = {
            MainScreen(
                onClickNextScreen = {
                    navHostController.navigate(Screen.Detail.getRouteWithArgs(it))
                },
                modifier = modifier
            )
        },
        detailScreenContent = {
            DetailScreen(
                userInput = it,
                onClickBack = {
                    navHostController.popBackStack(
                        route = Screen.Main.route,
                        inclusive = false
                    )
                },
                modifier = modifier
            )
        }
    )
}
