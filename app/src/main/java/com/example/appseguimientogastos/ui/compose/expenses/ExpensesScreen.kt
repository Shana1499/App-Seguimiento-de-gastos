package com.example.appseguimientogastos.ui.compose.expenses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.navigation.AddExpenses
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.view_model.BaseState
import com.example.appseguimientogastos.ui.view_model.ExpenseViewModelItem
import com.example.appseguimientogastos.ui.view_model.MainState
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel

@Composable
fun ExpensesScreenComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    // VIEWMODEL
    val viewModel: ExpenseViewModelItem = getViewModel()
    val state: MainState = viewModel.uiState.collectAsState().value
    val basestate: BaseState = viewModel.baseState.collectAsState().value

    // COMPOSABLES (UI)

    val currentScreen=Expenses

    CommonUI(
        navController = navController,
        currentScreen = currentScreen,
        drawerState = drawerState,
        scope = scope
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            ExpensesScreen(
                currentMonth = state.currentMonth,
                navController = navController,
                expensesScreen = Main,
                listData = state.expensesListByMonth,
                total = viewModel.getTotal(),
                onChangeScreen = viewModel::onChangeScreen
            )
        }
    }

}
@Composable
fun ExpensesScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    expensesScreen: MainComposeDestination,
    listData: List<Item>,
    total: Double,
    onChangeScreen:(onChangeScreenCompleted: () -> Unit) -> Unit
) {

    // COMPOSABLES (UI)
    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                ExpensesCard(
                    currentMonth = currentMonth,
                    navController = navController,
                    expensesScreen = expensesScreen,
                    listItemData = listData,
                    total = total,
                    onChangeScreen = onChangeScreen
                )
                val addScreen= AddExpenses
                AddButton(
                    screen = addScreen,
                    onTabSelected = { newAddScreen-> navController.navigateSingleTopTo(newAddScreen.route) })


            }
        }
    }
}