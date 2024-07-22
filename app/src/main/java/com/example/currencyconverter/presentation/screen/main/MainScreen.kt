package com.example.currencyconverter.presentation.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.getApplicationComponent
import com.example.currencyconverter.domain.Currency

@Composable
fun MainScreen(
    onClickNextScreen: (Currency) -> Unit
) {
    val component = getApplicationComponent()
    val viewModel: MainViewModel = viewModel(factory = component.getViewModelFactory())
    val currency = viewModel.listCurrency.collectAsState()

    Content(currency.value) { onClickNextScreen(it) }
}

@Composable
private fun Content(
    listCurrency: List<String>,
    onClickNextScreen: (Currency) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(all = 16.dp)
    ) {
        var currency by rememberSaveable { mutableStateOf(Currency()) }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            CurrencyTextField(currency.currentCurrency) {
                currency = currency.copy(amount = it)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DropDownListCurrency(currency.currentCurrency, listCurrency) {
                    currency = currency.copy(currentCurrency = it)
                }
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                DropDownListCurrency(currency.targetCurrency, listCurrency) {
                    currency = currency.copy(targetCurrency = it)
                }
            }

        }

        Box(
            modifier = Modifier.weight(2f),
            contentAlignment = Alignment.BottomCenter
        ) {
            var isEnabled by remember { mutableStateOf(true) }

            Button(
                onClick = {
                    if (isEnabled) {
                        isEnabled = false
                        onClickNextScreen(currency)
                    }
                },
                enabled = isEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.show_detail_btn))
            }
        }
    }
}

@Composable
private fun RowScope.DropDownListCurrency(
    currency: String,
    listCurrency: List<String>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = currency)
        IconButton(
            onClick = { expanded = true }
        ) {
            val icon = if (expanded)
                Icons.Default.KeyboardArrowUp
            else
                Icons.Default.KeyboardArrowDown

            Icon(imageVector = icon, contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(40.dp, 0.dp)
        ) {
            Box(
                modifier = modifier.size(width = 75.dp, height = 300.dp)
            ) {
                LazyColumn {
                    items(listCurrency) { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                onClick(item)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CurrencyTextField(
    currency: String,
    onChangedText: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        onValueChange = { input ->
            val filteredInput = input.filter { it.isDigit() || it == '.' }
            text = filteredInput
            onChangedText(text)
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.hint)) },
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        prefix = { Text("$currency: ") },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        )
    )
}