package com.example.appseguimientogastos.ui.view_model

import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.ui.view_model.utils.CoroutinesUtils
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExpenseViewModelItem(itemsRepository: ItemsRepository) :
    ItemBaseViewModel(itemsRepository = itemsRepository) {

    private val coroutinesUtils = CoroutinesUtils()

    // UI state
    private val _uiState = MutableStateFlow(MainState())//detecta cuando hay un cambio para repintar
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

}

