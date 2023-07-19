package com.example.appseguimientogastos.ui.compose.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.compose.expenses.ExpensesCard
import com.example.appseguimientogastos.ui.compose.income.IncomeCard
import com.example.appseguimientogastos.ui.compose.savings.SavingsCard
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.navigation.Savings
import com.example.appseguimientogastos.ui.view_model.BaseState
import com.example.appseguimientogastos.ui.view_model.MainState
import com.example.appseguimientogastos.ui.view_model.MainViewModelItem
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel


@Composable
fun MainScreenComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    // VIEWMODEL
    val viewModel: MainViewModelItem = getViewModel()
    val mainstate: MainState = viewModel.uiState.collectAsState().value
    val basestate: BaseState = viewModel.baseState.collectAsState().value

    // COMPOSABLES (UI)

    val currentScreen = Main
    if (mainstate.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(8.dp)
        )
    } else {
        CommonUI(
            navController = navController,
            currentScreen = currentScreen,
            drawerState = drawerState,
            scope = scope
        ) { innerPadding ->
            Column(modifier.padding(innerPadding)) {
                //en lugar de viwmodel poner las listas
                MainScreen(
                    currentMonth = mainstate.currentMonth,
                    navController = navController,
                    incomeScreen = Incomes,
                    expensesScreen = Expenses,
                    savingsScreen = Savings,
                    listIncomes = basestate.incomesList,
                    listExpenses = basestate.expensesList,
                    listSavings = basestate.savingsList,
                    progressList = mainstate.progressList
                )
            }

        }
    }

}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    incomeScreen: MainComposeDestination,
    expensesScreen: MainComposeDestination,
    savingsScreen: MainComposeDestination,
    listIncomes: List<Item>,
    listExpenses: List<Item>,
    listSavings: List<Item>,
    progressList: List<Float>,
) {
    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                DashBoardCard(
                    currentMonth = currentMonth, listIncomes = listIncomes,
                    listExpenses = listExpenses,
                    listSavings = listSavings,
                    progressList = progressList
                )
                IncomeCard(
                    modifier = modifier,
                    currentMonth = currentMonth,
                    navController = navController,
                    incomeScreen = incomeScreen,
                    listItemData = listIncomes
                )
                ExpensesCard(
                    modifier = modifier,
                    currentMonth = currentMonth,
                    navController = navController,
                    expensesScreen = expensesScreen,
                    listItemData = listExpenses

                )
                SavingsCard(
                    modifier = modifier,
                    currentMonth = currentMonth,
                    navController = navController,
                    savingsScreen = savingsScreen,
                    listItemData = listSavings

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