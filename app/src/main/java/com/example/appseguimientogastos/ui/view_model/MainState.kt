package com.example.appseguimientogastos.ui.view_model

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.getCurrentMonth
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination

/**
 * Data class that represents the game UI state
 */
data class MainState(
    var currentScreen: MainComposeDestination = Main,
    val currentMonth: MutableState<Month> = mutableStateOf(getCurrentMonth()),

    )


//val drawerState = rememberDrawerState(DrawerValue.Closed)
//        val scope = rememberCoroutineScope()