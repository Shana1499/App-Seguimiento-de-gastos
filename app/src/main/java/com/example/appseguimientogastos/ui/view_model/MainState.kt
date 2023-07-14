package com.example.appseguimientogastos.ui.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.data.model.getCurrentMonth
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination

/**
 * Data class that represents the game UI state
 */
data class MainState(
    val currentMonth: MutableState<Month> = mutableStateOf(getCurrentMonth()),
    val incomesListByMonth: List<Item> = listOf(),
    val expensesListByMonth: List<Item> = listOf(),
    val savingsListByMonth: List<Item> = listOf(),
    val progressList: List<Float> = listOf(),
    val isLoading: Boolean = true,

    )


//val drawerState = rememberDrawerState(DrawerValue.Closed)
//        val scope = rememberCoroutineScope()