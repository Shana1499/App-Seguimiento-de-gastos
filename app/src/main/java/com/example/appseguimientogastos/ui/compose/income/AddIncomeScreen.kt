package com.example.appseguimientogastos.ui.compose.income

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.appseguimientogastos.ui.compose.components.AddScreen
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.compose.expenses.AddExpensesScreen
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun AddIncomeScreenComposable(
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
        currentScreen = Incomes,
        drawerState = drawerState,
        scope = scope
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            AddIncomeScreen(
                currentMonth = currentMonth,
                newScreen = Incomes,
                navController = navController,
            )
        }
    }

}

@Composable
fun AddIncomeScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    newScreen: MainComposeDestination,
    navController: NavHostController
) {

    AddScreen(
        modifier = modifier,
        currentMonth = currentMonth,
        currentType = Type.INCOMES,
        stringResource(R.string.add_incomes),
        newScreen = newScreen,
        navController = navController
    )

}

@Preview(showBackground = true)
@Composable
fun AddIncomeScreenPreview() {
    MainComposeApp(startDestination = AddIncome.route)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddIncomeScreenDarkPreview() {
    MainComposeApp(startDestination = AddIncome.route)
}
