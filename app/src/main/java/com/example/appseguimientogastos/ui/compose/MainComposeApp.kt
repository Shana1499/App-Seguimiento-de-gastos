package com.example.appseguimientogastos.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.compose.expenses.AddExpenseScreenComposable
import com.example.appseguimientogastos.ui.compose.expenses.ExpensesScreenComposable
import com.example.appseguimientogastos.ui.compose.income.AddIncomeScreenComposable
import com.example.appseguimientogastos.ui.compose.income.IncomeScreenComposable
import com.example.appseguimientogastos.ui.compose.mainscreen.MainScreenComposable
import com.example.appseguimientogastos.ui.compose.savings.AddSavingScreenComposable
import com.example.appseguimientogastos.ui.compose.savings.SavingsScreenComposable
import com.example.appseguimientogastos.ui.navigation.AddExpenses
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.AddSavings
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeNavHost
import com.example.appseguimientogastos.ui.navigation.Savings
import com.example.compose.AppSeguimientoGastosTheme


@Composable
fun MainComposeApp(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Main.route,
) {
    AppSeguimientoGastosTheme {

        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val nav = listOf(
            { navController.navigate(Main.route) },
            { navController.navigate(Incomes.route) },
            { navController.navigate(Expenses.route) },
            { navController.navigate(Savings.route) }
        )

        CommonUI(
            navController = navController,
            currentScreen = Main,
            drawerState = drawerState,
            scope = scope
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {

                MainComposeNavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {

                    composable(route = Main.route) {

                        MainScreenComposable(
                            onNavigateNext = nav
                        )
                    }
                    composable(route = Incomes.route) {
                        IncomeScreenComposable(
                            onNavigateBack = { navController.navigate(Main.route)},
                            onNavigateNext = { navController.navigate(AddIncome.route) },
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable(route = Expenses.route) {
                        ExpensesScreenComposable(
                            onNavigateBack = { navController.navigate(Main.route) },
                            onNavigateNext = { navController.navigate(AddExpenses.route) },
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable(route = Savings.route) {
                        SavingsScreenComposable(
                            onNavigateBack = { navController.navigate(Main.route) },
                            onNavigateNext = { navController.navigate(AddSavings.route) },
                        )
                    }

                    composable(route = AddIncome.route) {
                        AddIncomeScreenComposable(
                            onNavigateBack = { navController.navigate(Incomes.route) },
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable(route = AddExpenses.route) {
                        AddExpenseScreenComposable(
                            onNavigateBack = { navController.navigate(Expenses.route) },
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable(route = AddSavings.route) {
                        AddSavingScreenComposable(
                            onNavigateBack = { navController.navigate(Savings.route) },
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }

                }
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
