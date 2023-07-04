package com.example.appseguimientogastos.ui.compose.savings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.ui.navigation.AddSavings
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.compose.expenses.ExpensesScreen
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.Savings
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel

@Composable
fun SavingsScreenComposable(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    // VIEWMODEL
    /*val viewModel: MainViewModel = getViewModel()
    val state: MainState = viewModel.uiState.collectAsState().value*/

    // COMPOSABLES (UI)

    CommonUI(
        navController = navController,
        currentScreen = Savings,
        drawerState = drawerState,
        scope = scope
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            SavingsScreen(
                currentMonth = currentMonth,
                navController = navController,
                savingsScreen = Main,
            )
        }
    }
}
@Composable
fun SavingsScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    savingsScreen: MainComposeDestination,
) {
    // VIEWMODEL
    /*val viewModel: IncomeViewModel = getViewModel()
    val state: IncomeState = viewModel.uiState.collectAsState().value*/


    // COMPOSABLES (UI)

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {

                SavingsCard(
                    navController = navController,
                    currentMonth = currentMonth,
                    savingsScreen = savingsScreen
                )
                val addScreen= AddSavings
                AddButton(
                    screen = addScreen,
                    onTabSelected = { newAddScreen-> navController.navigateSingleTopTo(newAddScreen.route) })


            }
        }
    }
}