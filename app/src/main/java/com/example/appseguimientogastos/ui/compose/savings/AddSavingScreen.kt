package com.example.appseguimientogastos.ui.compose.savings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.compose.components.AddScreen
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.compose.expenses.AddExpensesScreen
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.navigation.AddSavings
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.navigation.Savings
import kotlinx.coroutines.CoroutineScope

@Composable
fun AddSavingScreenComposable(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    // VIEWMODEL
    /*val viewModel: AddViewModel = getViewModel()
    val state: AddState = viewModel.uiState.collectAsState().value*/

    // COMPOSABLES (UI)

    CommonUI(
        navController = navController,
        currentScreen = AddSavings,
        drawerState = drawerState,
        scope = scope
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            AddSavingsScreen(
                currentMonth = currentMonth,
                newScreen = Savings,
                navController = navController
            )
        }
    }

}
@Composable
fun AddSavingsScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    newScreen: MainComposeDestination,
    navController: NavHostController
) {

    AddScreen(
        Modifier,
        currentMonth = currentMonth,
        currentType = Type.SAVINGS,
        stringResource(R.string.add_savings),
        newScreen = newScreen,
        navController = navController
    )

}

