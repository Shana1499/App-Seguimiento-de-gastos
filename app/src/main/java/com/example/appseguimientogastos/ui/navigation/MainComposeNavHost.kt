/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appseguimientogastos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.compose.components.AddExpensesScreen
import com.example.appseguimientogastos.ui.compose.components.AddIncomeScreen
import com.example.appseguimientogastos.ui.compose.components.AddSavingsScreen
import com.example.appseguimientogastos.ui.compose.expenses.ExpensesScreen
import com.example.appseguimientogastos.ui.compose.income.IncomeScreen
import com.example.appseguimientogastos.ui.compose.mainscreen.MainScreen
import com.example.appseguimientogastos.ui.compose.savings.SavingsScreen
import com.example.appseguimientogastos.ui.navigation.AddExpenses
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.AddSavings
import com.example.appseguimientogastos.ui.navigation.Expenses
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.navigation.Savings


@Composable
fun MainComposeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    allScreens: List<MainComposeDestination>,

    ) {
    NavHost(
        navController = navController,
        startDestination = Main.route,
        modifier = modifier
    ) {

        val homeScreen = allScreens[0]
        val incomeScreen = allScreens[1]
        val expensesScreen = allScreens[2]
        val savingsScreen = allScreens[3]

        composable(route = Main.route) {
            MainScreen(
                currentMonth = currentMonth,
                navController = navController,
                incomeScreen = incomeScreen,
                expensesScreen = expensesScreen,
                savingsScreen = savingsScreen,


            )
        }
        composable(route = Incomes.route) {
            IncomeScreen(
                currentMonth = currentMonth,
                navController = navController,
                incomeScreen = homeScreen
            )
        }
        composable(route = Expenses.route) {
            ExpensesScreen(
                currentMonth = currentMonth,
                navController = navController,
                expensesScreen = homeScreen,

            )
        }
        composable(route = Savings.route) {
            SavingsScreen(
                currentMonth = currentMonth,
                navController = navController,
                savingsScreen = homeScreen,

            )
        }

        composable(route= AddIncome.route){
            AddIncomeScreen(
                currentMonth = currentMonth,
                newScreen = incomeScreen,
                navController = navController
            )
        }
        composable(route= AddExpenses.route){
            AddExpensesScreen(
                currentMonth = currentMonth,
                newScreen = expensesScreen,
                navController = navController
            )
        }
        composable(route= AddSavings.route){
            AddSavingsScreen(
                currentMonth = currentMonth,
                newScreen = savingsScreen,
                navController = navController
            )
        }


    }
}



fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

