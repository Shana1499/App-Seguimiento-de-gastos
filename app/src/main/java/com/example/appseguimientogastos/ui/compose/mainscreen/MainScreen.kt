package com.example.appseguimientogastos.ui.compose.mainscreen

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.compose.MainComposeApp
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
import org.koin.androidx.compose.getViewModel


@Composable
fun MainScreenComposable(
    modifier: Modifier = Modifier,
    onNavigateNext: List<() -> Unit>,
) {
    // VIEWMODEL
    val viewModel: MainViewModelItem = getViewModel()
    val mainstate: MainState = viewModel.uiState.collectAsState().value
    val basestate: BaseState = viewModel.baseState.collectAsState().value

    // COMPOSABLES (UI)

    val currentScreen = Main
    BackHandler(enabled = true) {}
    if (mainstate.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(8.dp)
        )
    } else {
        MainScreen(
            currentMonth = mainstate.currentMonth,
            incomeScreen = Incomes,
            expensesScreen = Expenses,
            savingsScreen = Savings,
            listIncomes = mainstate.incomesListByMonth,
            listExpenses = mainstate.expensesListByMonth,
            listSavings = mainstate.savingsListByMonth,
            progressList = mainstate.progressList,
            onUpdateMonth = viewModel::onUpdateMonth,
            listIGA = viewModel.getTotalIGA(),
            budget = mainstate.budget,
            onChangeScreen = viewModel::onChangeScreen,
            onNavigateNext = onNavigateNext
        )
    }


}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    incomeScreen: MainComposeDestination,
    expensesScreen: MainComposeDestination,
    savingsScreen: MainComposeDestination,
    listIncomes: List<Item>,
    listExpenses: List<Item>,
    listSavings: List<Item>,
    progressList: List<Float>,
    onUpdateMonth: (currentMonth: MutableState<Month>) -> Unit,
    listIGA: List<Double>,
    budget: Double,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit,
    onNavigateNext: List<() -> Unit>
) {
    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                DashBoardCard(
                    currentMonth = currentMonth,
                    progressList = progressList,
                    onUpdateMonth = onUpdateMonth,
                    budget = budget

                )
                IncomeCard(
                    modifier = modifier,
                    currentMonth = currentMonth,
                    incomeScreen = incomeScreen,
                    listItemData = listIncomes,
                    total = listIGA[0],
                    onChangeScreen = onChangeScreen,
                    onNavigateNext = onNavigateNext[1]
                )
                ExpensesCard(
                    modifier = modifier,
                    currentMonth = currentMonth,
                    expensesScreen = expensesScreen,
                    listItemData = listExpenses,
                    total = listIGA[1],
                    onChangeScreen = onChangeScreen,
                    onNavigateNext = onNavigateNext[2]

                )
                SavingsCard(
                    modifier = modifier,
                    currentMonth = currentMonth,
                    savingsScreen = savingsScreen,
                    listItemData = listSavings,
                    total = listIGA[2],
                    onChangeScreen = onChangeScreen,
                    onNavigateNext = onNavigateNext[3]

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