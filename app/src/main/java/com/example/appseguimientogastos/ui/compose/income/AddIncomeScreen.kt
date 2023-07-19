package com.example.appseguimientogastos.ui.compose.income

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Type
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.appseguimientogastos.ui.compose.components.AddScreen
import com.example.appseguimientogastos.ui.compose.components.CommonUI
import com.example.appseguimientogastos.ui.navigation.AddIncome
import com.example.appseguimientogastos.ui.navigation.Incomes
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.view_model.AddViewModelItem
import com.example.appseguimientogastos.ui.view_model.BaseState
import com.example.appseguimientogastos.ui.view_model.MainState
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel

@Composable
fun AddIncomeScreenComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    // VIEWMODEL
    val viewModel: AddViewModelItem = getViewModel()
    val state: MainState = viewModel.uiState.collectAsState().value
    val basestate: BaseState = viewModel.baseState.collectAsState().value

    // COMPOSABLES (UI)
    val currentScreen = AddIncome

    CommonUI(
        navController = navController,
        currentScreen = currentScreen,
        drawerState = drawerState,
        scope = scope
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            AddIncomeScreen(
                currentMonth = state.currentMonth,
                newScreen = Incomes,
                navController = navController,
                onAddItem = viewModel::addItem
            )
        }
    }

}

@Composable
fun AddIncomeScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    newScreen: MainComposeDestination,
    navController: NavHostController,
    onAddItem: (origin: String, price:String, month:String, type:Type, onAddItemCompleted:()->Unit)->Unit
) {

    AddScreen(
        modifier = modifier,
        currentMonth = currentMonth,
        currentType = Type.INCOMES,
        stringResource(R.string.add_incomes),
        newScreen = newScreen,
        navController = navController,
        onAddItem= onAddItem
    )

}

@Preview(showBackground = true)
@Composable
fun AddIncomeScreenPreview() {
    MainComposeApp(startDestination = AddIncome.route)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddIncomeScreenDarkPreview() {
    MainComposeApp(startDestination = AddIncome.route)
}
