package com.example.currencyconverter.presentation.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.Currency
import com.example.currencyconverter.domain.DataState
import com.example.currencyconverter.domain.UserInput
import com.example.currencyconverter.getApplicationComponent
import com.example.currencyconverter.presentation.common.ErrorScreen
import com.example.currencyconverter.presentation.common.LoadingScreen

@Composable
fun DetailScreen(
    userInput: UserInput,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val component = getApplicationComponent()
    val factory = DetailViewModel
        .provideFactory(component.detailViewModelFactory(), userInput)
    val viewModel: DetailViewModel = viewModel(factory = factory)
    val currencyState = viewModel.currencyData.collectAsState()

    Column(modifier = modifier) {
        BackButton { onClickBack() }

        when (val currentState = currencyState.value) {
            DataState.Initial -> {}
            DataState.Loading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }
            is DataState.Success -> {
                DetailContent(
                    currency = currentState.data,
                    modifier = modifier
                )
            }
            is DataState.Error -> {
                ErrorScreen(
                    errorMessage = stringResource(R.string.error_convert_currency),
                    onClick = { viewModel.loadDetailData() },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun DetailContent(
    currency: Currency,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.update_last_time, currency.lastTimeUpdate),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
        CurrencyCard(currency)
    }
}

@Composable
private fun CurrencyCard(currency: Currency) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = currency.userInput.currentCurrency,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            FormattedCurrencyText(
                baseCurrency = currency.userInput.currentCurrency,
                targetCurrency = currency.userInput.targetCurrency,
                currencyAmount = 1.00,
                targetAmount = currency.amountInTargetCurrency.first,
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        FormattedCurrencyText(
            baseCurrency = currency.userInput.currentCurrency,
            targetCurrency = currency.userInput.targetCurrency,
            currencyAmount = currency.userInput.amount.toDouble(),
            targetAmount = currency.amountInTargetCurrency.second,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@SuppressLint("DefaultLocale")
@Composable
private fun FormattedCurrencyText(
    baseCurrency: String,
    targetCurrency: String,
    currencyAmount: Double,
    targetAmount: Double,
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray,
    fontSize: TextUnit = 22.sp
) {
    val formattedCurrencyAmount = String.format("%.2f", currencyAmount).replace(',', '.')
    val formattedTargetAmount = String.format("%.2f", targetAmount).replace(',', '.')

    Text(
        text = "$formattedCurrencyAmount $baseCurrency = $formattedTargetAmount $targetCurrency",
        color = color,
        modifier = modifier,
        fontSize = fontSize
    )
}

@Composable
private fun BackButton(
    onClickBack: () -> Unit
) {
    IconButton(
        onClick = { onClickBack() }
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
    }
}