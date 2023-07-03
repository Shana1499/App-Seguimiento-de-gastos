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

package com.example.appseguimientogastos

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Contract for information needed on every navigation destination
 */
interface MainComposeDestination {
    val icon: ImageVector
    val name: String
    val route: String
}

/**
 * app navigation destinations
 */
object Main : MainComposeDestination {
    override val icon = Icons.Filled.Home
    override val name = "Home"
    override val route = "main"
}

object Incomes : MainComposeDestination {
    override val icon = Icons.Filled.AttachMoney
    override val name = "Incomes"
    override val route = "income"
}

object Expenses : MainComposeDestination {
    override val icon = Icons.Filled.MoneyOff
    override val name = "Expenses"
    override val route = "expenses"
}

object Savings : MainComposeDestination {
    override val icon = Icons.Filled.MoneyOff
    override val name = "Savings"
    override val route = "saving"
}

object AddIncome : MainComposeDestination {
    override val icon = Icons.Filled.MoneyOff
    override val name = "AddIncome"
    override val route = "add-income"
}
object AddExpenses : MainComposeDestination {
    override val icon = Icons.Filled.MoneyOff
    override val name = "AddExpenses"
    override val route = "add-expenses"
}
object AddSavings : MainComposeDestination {
    override val icon = Icons.Filled.MoneyOff
    override val name = "AddSavings"
    override val route = "add-savings"
}


// Screens to be displayed in the top TabRow
val tabRowScreens = listOf(Main, Incomes, Expenses, Savings)
