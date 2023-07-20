package com.example.appseguimientogastos.ui.compose.savings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.ui.compose.components.AddButton
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.navigation.Savings
import com.example.appseguimientogastos.ui.view_model.BaseState
import com.example.appseguimientogastos.ui.view_model.MainState
import com.example.appseguimientogastos.ui.view_model.SavingsViewModelItem
import org.koin.androidx.compose.getViewModel

@Composable
fun SavingsScreenComposable(
    modifier: Modifier = Modifier,
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    // VIEWMODEL
    val viewModel: SavingsViewModelItem = getViewModel()
    val state: MainState = viewModel.uiState.collectAsState().value
    val basestate: BaseState = viewModel.baseState.collectAsState().value

    // COMPOSABLES (UI)
    val currentScreen = Savings
    BackHandler(enabled = true) {}
    SavingsScreen(
        currentMonth = state.currentMonth,
        savingsScreen = Main,
        listData = state.savingsListByMonth,
        total = viewModel.getTotal(),
        onChangeScreen = viewModel::onChangeScreen,
        onNavigateNext = onNavigateNext,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun SavingsScreen(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
    savingsScreen: MainComposeDestination,
    listData: List<Item>,
    total: Double,
    onChangeScreen: (onChangeScreenCompleted: () -> Unit) -> Unit,
    onNavigateNext: () -> Unit,
    onNavigateBack: () -> Unit

) {
    // COMPOSABLES (UI)

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {

                SavingsCard(
                    currentMonth = currentMonth,
                    savingsScreen = savingsScreen,
                    listItemData = listData,
                    total = total,
                    onChangeScreen = onChangeScreen,
                    onNavigateNext = onNavigateBack
                )
                AddButton(
                    onTabSelected = onNavigateNext
                )


            }
        }
    }
}