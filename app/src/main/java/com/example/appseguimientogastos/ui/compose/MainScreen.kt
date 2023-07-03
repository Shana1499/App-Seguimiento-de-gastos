package com.example.appseguimientogastos.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.MainComposeDestination
import com.example.appseguimientogastos.ui.compose.expenses.ExpensesCard
import com.example.appseguimientogastos.ui.compose.income.IncomeCard
import com.example.appseguimientogastos.ui.compose.mainscreen.DashBoardCard
import com.example.appseguimientogastos.ui.compose.savings.SavingsCard
import com.example.appseguimientogastos.ui.data.Month

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    incomeScreen: MainComposeDestination,
    expensesScreen: MainComposeDestination,
    savingsScreen: MainComposeDestination,

    ) {

    //VIEW MODEL

    //UI

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                DashBoardCard(currentMonth = currentMonth)
                IncomeCard(
                    modifier = modifier,
                    currentMonth = currentMonth, navController = navController,
                    incomeScreen=incomeScreen
                )
                ExpensesCard(
                    modifier = modifier,
                    currentMonth = currentMonth,
                    navController = navController,
                    expensesScreen=expensesScreen
                )
                SavingsCard(
                    modifier = modifier,
                    currentMonth = currentMonth,
                    navController = navController,
                    savingsScreen = savingsScreen
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainComposeAppPreview() {
    MainComposeApp()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainComposeAppDarkPreview() {
    MainComposeApp()
}