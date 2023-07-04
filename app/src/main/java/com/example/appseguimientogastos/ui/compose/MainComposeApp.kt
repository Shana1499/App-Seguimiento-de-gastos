package com.example.appseguimientogastos.ui.compose

import android.content.res.Configuration
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.ui.compose.expenses.AddExpenseScreenComposable
import com.example.appseguimientogastos.ui.compose.expenses.ExpensesScreenComposable
import com.example.appseguimientogastos.ui.compose.income.AddIncomeScreenComposable
import com.example.appseguimientogastos.ui.compose.income.IncomeScreenComposable
import com.example.appseguimientogastos.ui.compose.mainscreen.MainScreenComposable
import com.example.appseguimientogastos.ui.compose.savings.AddSavingScreenComposable
import com.example.appseguimientogastos.ui.compose.savings.SavingsScreenComposable
import com.example.appseguimientogastos.ui.data.getCurrentMonth
import com.example.appseguimientogastos.ui.navigation.AddExpenses
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.AddSavings
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeNavHost
import com.example.appseguimientogastos.ui.navigation.Savings
import com.example.appseguimientogastos.ui.navigation.tabRowScreens
import com.example.compose.AppSeguimientoGastosTheme


@Composable
fun MainComposeApp(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Main.route,
) {
    AppSeguimientoGastosTheme {
        // hay que poner aqui MainComposeNavHost
        // list of nav screens + icons
        val currentMonth = remember { mutableStateOf(getCurrentMonth()) }

        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        MainComposeNavHost(
            navController = navController,
            startDestination = startDestination
        ) {

            composable(route = Main.route) {
                MainScreenComposable(
                    currentMonth = currentMonth,
                    navController = navController,
                    drawerState = drawerState,
                    scope = scope
                )
            }
            composable(route = Incomes.route) {
                IncomeScreenComposable(
                    currentMonth = currentMonth,
                    navController = navController, drawerState = drawerState,
                    scope = scope
                )
            }
            composable(route = Expenses.route) {
                ExpensesScreenComposable(
                    currentMonth = currentMonth,
                    navController = navController, drawerState = drawerState,
                    scope = scope
                )
            }
            composable(route = Savings.route) {
                SavingsScreenComposable(
                    currentMonth = currentMonth,
                    navController = navController, drawerState = drawerState,
                    scope = scope
                )
            }

            composable(route = AddIncome.route) {
                AddIncomeScreenComposable(
                    currentMonth = currentMonth,
                    navController = navController,
                    drawerState = drawerState,
                    scope = scope
                )
            }
            composable(route = AddExpenses.route) {
                AddExpenseScreenComposable(
                    currentMonth = currentMonth,
                    navController = navController,
                    drawerState = drawerState,
                    scope = scope
                )
            }
            composable(route = AddSavings.route) {
                AddSavingScreenComposable(
                    currentMonth = currentMonth,
                    navController = navController,
                    drawerState = drawerState,
                    scope = scope
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
