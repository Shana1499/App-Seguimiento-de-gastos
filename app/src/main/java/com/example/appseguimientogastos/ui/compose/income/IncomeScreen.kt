package com.example.appseguimientogastos.ui.compose.income

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.view_model.BaseState
import com.example.appseguimientogastos.ui.view_model.IncomeViewModelItem
import com.example.appseguimientogastos.ui.view_model.MainState
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel

@Composable
fun IncomeScreenComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    // VIEWMODEL
    val viewModel: IncomeViewModelItem = getViewModel()
    val state: MainState = viewModel.uiState.collectAsState().value
    val basestate: BaseState = viewModel.baseState.collectAsState().value


    // COMPOSABLES (UI)
    val currentScreen = Incomes

    CommonUI(
        navController = navController,
        currentScreen = currentScreen,
        drawerState = drawerState,
        scope = scope
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            IncomeScreen(
                currentMonth = state.currentMonth,
                navController = navController,
                incomeScreen = Main,
                listData = state.incomesListByMonth,
                total = viewModel.getTotal(),
                onChangeScreen = viewModel::onChangeScreen
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
    listData: List<Item>,
    total: Double,
    onChangeScreen:(onChangeScreenCompleted: () -> Unit) -> Unit
) {

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {

                IncomeCard(
                    navController = navController,
                    currentMonth = currentMonth,
                    incomeScreen = incomeScreen,
                    listItemData = listData,
                    total = total,
                    onChangeScreen = onChangeScreen
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

