package com.example.appseguimientogastos.ui.compose.income

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.compose.components.OverviewCard
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination


/**
 * Incomes Card
 * */

@Composable
fun IncomeCard(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    incomeScreen: MainComposeDestination,
    listItemData: List<Item>,
    total: Double,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit,
    onNavigateNext: () -> Unit,

    ) {

    OverviewCard(
        modifier = modifier,
        title = stringResource(R.string.ingresos),
        currentMonth = currentMonth,
        newScreen = incomeScreen,
        listItemData = listItemData,
        total = total,
        onChangeScreen =onChangeScreen,
        onNavigateNext = onNavigateNext
    )

}
