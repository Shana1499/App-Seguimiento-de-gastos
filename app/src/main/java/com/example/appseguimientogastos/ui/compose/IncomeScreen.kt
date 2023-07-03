package com.example.appseguimientogastos.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.ui.AddIncome
import com.example.appseguimientogastos.ui.Main
import com.example.appseguimientogastos.ui.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.getCurrentMonth
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.monthList
import com.example.appseguimientogastos.ui.domain.item.model.Item
import com.example.appseguimientogastos.ui.navigateSingleTopTo
import com.example.appseguimientogastos.ui.tabRowScreens
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.compose.components.OverviewCard
import com.example.compose.AppSeguimientoGastosTheme

@Composable
fun IncomeScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    incomeScreen: MainComposeDestination,
) {
    val listIncomes = listOf<Item>(
        Item(
            id = 0,
            origin = "prueba1Income",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), Item(
            id = 0,
            origin = "prueba2Income",
            price = 14.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), Item(
            id = 0,
            origin = "prueba3Income",
            price = 16.0,
            type = Type.INCOMES.typeName,
            month = monthList[6].name
        ), Item(
            id = 0,
            origin = "prueba4Income",
            price = 12.0,
            type = Type.INCOMES.typeName,
            month = monthList[0].name
        )
    )

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                OverviewCard(
                    modifier = modifier,
                    title = stringResource(R.string.ingresos),
                    currentMonth = currentMonth,
                    newScreen = incomeScreen,
                    navController = navController,
                    listItem = listIncomes,
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

    // list of nav screens + icons
    val currentMonth = remember { mutableStateOf(getCurrentMonth()) }

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        tabRowScreens.find { it.route == currentDestination?.route } ?: Main

    AppSeguimientoGastosTheme {
        IncomeScreen(
            Modifier,
            currentMonth = currentMonth,
            navController = navController,
            incomeScreen = currentScreen,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun IncomeScreenDarkPreview() {
    // list of nav screens + icons
    val currentMonth = remember { mutableStateOf(getCurrentMonth()) }

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        tabRowScreens.find { it.route == currentDestination?.route } ?: Main
    AppSeguimientoGastosTheme {
        IncomeScreen(
            Modifier,
            currentMonth = currentMonth,
            navController = navController,
            incomeScreen = currentScreen,
        )
    }
}



