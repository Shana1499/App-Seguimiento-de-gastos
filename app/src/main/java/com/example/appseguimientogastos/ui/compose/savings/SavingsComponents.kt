package com.example.appseguimientogastos.ui.compose.savings

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
 * Card de Ahorro
 * */
@Composable
fun SavingsCard(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    savingsScreen: MainComposeDestination,
    listItemData: List<Item>,
    total: Double,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit,
    onNavigateNext: () -> Unit,

    ) {

    OverviewCard(
        modifier,
        stringResource(R.string.ahorro),
        currentMonth,
        savingsScreen,
        listItemData,
        total,
        onChangeScreen,
        onNavigateNext,
    )

}
