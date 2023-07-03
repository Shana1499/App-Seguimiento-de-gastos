package com.example.appseguimientogastos.ui.compose.expenses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.Month
import com.example.appseguimientogastos.data.item.local.ItemVO
import com.example.appseguimientogastos.data.item.local.Type
import com.example.appseguimientogastos.data.monthList
import com.example.appseguimientogastos.ui.compose.components.OverviewCard


/**
 * Card de Gastos
 * */
@Composable
fun ExpensesCard(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController, expensesScreen: MainComposeDestination,

    ) {
    val listIncomes = listOf<ItemVO>(
        ItemVO(
            id = 0,
            origin = "prueba1Expenses",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), ItemVO(
            id = 0,
            origin = "prueba2Expenses",
            price = 14.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), ItemVO(
            id = 0,
            origin = "prueba3Expenses",
            price = 16.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), ItemVO(
            id = 0,
            origin = "prueba4Expenses",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = monthList[0].name
        )
    )

    OverviewCard(
        modifier,
        stringResource(R.string.gastos),
        currentMonth,
        expensesScreen,
        navController,
        listIncomes,
    )

}
