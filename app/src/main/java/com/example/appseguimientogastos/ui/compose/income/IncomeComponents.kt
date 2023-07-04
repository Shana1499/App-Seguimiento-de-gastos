package com.example.appseguimientogastos.ui.compose.income

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.compose.components.OverviewCard
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.monthList


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

    val listIncomes = listOf<ItemVO>(
        ItemVO(
            id = 0,
            origin = "prueba1Incomes",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), ItemVO(
            id = 0,
            origin = "prueba2Incomes",
            price = 14.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), ItemVO(
            id = 0,
            origin = "prueba3Incomes",
            price = 16.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ),
        ItemVO(
            id = 0,
            origin = "prueba4Incomes",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = monthList[0].name
        )
    )

    listIncomes.forEach { item-> }
    OverviewCard(
        modifier = modifier,
        title = stringResource(R.string.ingresos),
        currentMonth = currentMonth,
        newScreen = incomeScreen, navController = navController,
        listItemData=listIncomes
    )

}
