package com.example.appseguimientogastos.ui.compose.income

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.ui.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.domain.item.model.Item
import com.example.appseguimientogastos.ui.compose.components.OverviewCard


/**
 * Incomes Card
 * */

@Composable
fun IncomeCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentMonth: MutableState<Month>,
    incomeScreen: MainComposeDestination,
) {

    val listIncomes = listOf(
        Item(
            id = 0,
            origin = "prueba1Income",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = currentMonth.value.name
        ), Item(
            id = 0,
            origin = "prueba2Income",
            price = 14.0,
            type = Type.INCOMES.typeName,
            month = currentMonth.value.name
        ), Item(
            id = 0,
            origin = "prueba3Income",
            price = 16.0,
            type = Type.INCOMES.typeName,
            month = currentMonth.value.name
        )
    )
    OverviewCard(
        modifier = modifier,
        title = stringResource(R.string.ingresos),
        currentMonth = currentMonth,
        newScreen = incomeScreen, navController = navController,
        listItem=listIncomes
    )

}
