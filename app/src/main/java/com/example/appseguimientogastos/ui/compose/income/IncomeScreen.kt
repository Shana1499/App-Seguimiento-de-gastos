package com.example.appseguimientogastos.ui.compose.income

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.navigation.tabRowScreens
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.compose.mainscreen.MainScreen
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.getCurrentMonth
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.Savings
import com.example.appseguimientogastos.ui.view_model.BaseViewModel
import com.example.compose.AppSeguimientoGastosTheme
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel

@Composable
fun IncomeScreenComposable(
    modifier: Modifier=Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    // VIEWMODEL
    /* val viewModel: IncomeViewModel = getViewModel()
     val state: IncomeState = viewModel.uiState.collectAsState().value*/


    // COMPOSABLES (UI)

    CommonUI(
        navController = navController,
        currentScreen = Incomes,
        drawerState = drawerState,
        scope = scope
    ) {innerPadding ->
        Column(modifier.padding(innerPadding)) {
            IncomeScreen(
                currentMonth = currentMonth,
                navController = navController,
                incomeScreen = Main,
            )
        }
    }

}

@Composable
fun IncomeScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    incomeScreen: MainComposeDestination,
) {

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {

                IncomeCard(
                    navController = navController,
                    currentMonth = currentMonth,
                    incomeScreen = incomeScreen
                )
                val addScreen = AddIncome
                AddButton(
                    screen = addScreen,
                    onTabSelected = { newAddScreen -> navController.navigateSingleTopTo(newAddScreen.route) })


            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun IncomeScreenPreview() {
    MainComposeApp(startDestination = Incomes.route)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun IncomeScreenDarkPreview() {
    MainComposeApp(startDestination = Incomes.route)
}

