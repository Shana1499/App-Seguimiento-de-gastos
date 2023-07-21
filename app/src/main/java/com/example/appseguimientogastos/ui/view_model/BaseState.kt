package com.example.appseguimientogastos.ui.view_model

import com.example.appseguimientogastos.domain.model.Item

data class BaseState(
    val incomesList: List<Item> = listOf(),
    val expensesList: List<Item> = listOf(),
    val savingsList: List<Item> = listOf(),
    val isLoading: Boolean = true,
)