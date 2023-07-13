package com.example.appseguimientogastos.ui.compose.savings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.ui.navigation.AddSavings
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.Savings
import com.example.appseguimientogastos.ui.view_model.MainState
import com.example.appseguimientogastos.ui.view_model.SavingsViewModelItem
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel

@Composable
fun SavingsScreenComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    // VIEWMODEL
    val viewModel: SavingsViewModelItem = getViewModel()
    val state: MainState = viewModel.uiState.collectAsState().value

    // COMPOSABLES (UI)
    state.currentScreen = Savings

    CommonUI(
        navController = navController,
        currentScreen = state.currentScreen,
        drawerState = drawerState,
        scope = scope
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            SavingsScreen(
                currentMonth = state.currentMonth,
                navController = navController,
                savingsScreen = Main,
                viewModel = viewModel
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
    viewModel: SavingsViewModelItem,
) {
    // COMPOSABLES (UI)

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {

                SavingsCard(
                    navController = navController,
                    currentMonth = currentMonth,
                    savingsScreen = savingsScreen,
                    listItemData = viewModel.getItemByMonthList(currentMonth, viewModel.savingsList)
                )
                val addScreen= AddSavings
                AddButton(
                    screen = addScreen,
                    onTabSelected = { newAddScreen-> navController.navigateSingleTopTo(newAddScreen.route) })


            }
        }
    }
}