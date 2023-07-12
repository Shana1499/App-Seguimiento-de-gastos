package com.example.appseguimientogastos.ui.compose.savings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.monthList
import com.example.appseguimientogastos.ui.compose.components.OverviewCard

/**
 * Card de Ahorro
 * */
@Composable
fun SavingsCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentMonth: MutableState<Month>,
    savingsScreen: MainComposeDestination,
    listItemData: List<ItemVO>,

    ) {

    OverviewCard(
        modifier,
        stringResource(R.string.ahorro),
        currentMonth,
        savingsScreen,
        navController,
        listItemData,
    )

}
