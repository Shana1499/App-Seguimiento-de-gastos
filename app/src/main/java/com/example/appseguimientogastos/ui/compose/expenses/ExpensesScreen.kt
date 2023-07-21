package com.example.appseguimientogastos.ui.compose.expenses

import androidx.activity.compose.BackHandler
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
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
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
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    // VIEWMODEL
    val viewModel: ExpenseViewModelItem = getViewModel()
    val state: MainState = viewModel.uiState.collectAsState().value
    val basestate: BaseState = viewModel.baseState.collectAsState().value

    // COMPOSABLES (UI)

    val currentScreen = Expenses
    BackHandler(enabled = true) {}
    ExpensesScreen(
        currentMonth = state.currentMonth,
        expensesScreen = Main,
        listData = state.expensesListByMonth,
        total = viewModel.getTotal(),
        onChangeScreen = viewModel::onChangeScreen,
        onNavigateNext = onNavigateNext,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun ExpensesScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    expensesScreen: MainComposeDestination,
    listData: List<Item>,
    total: Double,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit,
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit
) {

    // COMPOSABLES (UI)
    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                ExpensesCard(
                    currentMonth = currentMonth,
                    expensesScreen = expensesScreen,
                    listItemData = listData,
                    total = total,
                    onChangeScreen = onChangeScreen,
                    onNavigateNext = onNavigateBack
                )
                AddButton(
                    onTabSelected = onNavigateNext
                )


            }
        }
    }
}