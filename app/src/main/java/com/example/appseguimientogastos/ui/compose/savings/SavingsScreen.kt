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
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.navigation.AddSavings
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.navigation.Savings
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.view_model.BaseState
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
    val basestate: BaseState = viewModel.baseState.collectAsState().value

    // COMPOSABLES (UI)
    val currentScreen = Savings

    CommonUI(
        navController = navController,
        currentScreen = currentScreen,
        drawerState = drawerState,
        scope = scope
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            SavingsScreen(
                currentMonth = state.currentMonth,
                navController = navController,
                savingsScreen = Main,
                listData = state.savingsListByMonth,
                total = viewModel.getTotal(),
                onChangeScreen = viewModel::onChangeScreen
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
    listData: List<Item>,
    total: Double,
    onChangeScreen:(onChangeScreenCompleted: () -> Unit) -> Unit

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
                    listItemData = listData,
                    total = total,
                    onChangeScreen = onChangeScreen
                )
                val addScreen= AddSavings
                AddButton(
                    screen = addScreen,
                    onTabSelected = { newAddScreen-> navController.navigateSingleTopTo(newAddScreen.route) })


            }
        }
    }
}