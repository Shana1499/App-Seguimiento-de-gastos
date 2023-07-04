package com.example.appseguimientogastos.ui.compose.income

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.navigation.tabRowScreens
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.getCurrentMonth
import com.example.appseguimientogastos.ui.view_model.MainViewModel
import com.example.compose.AppSeguimientoGastosTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun IncomeScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    navController: NavHostController,
    incomeScreen: MainComposeDestination,
) {
    // VIEWMODEL
   /* val viewModel: IncomeViewModel = getViewModel()
    val state: IncomeState = viewModel.uiState.collectAsState().value*/


    // COMPOSABLES (UI)
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


