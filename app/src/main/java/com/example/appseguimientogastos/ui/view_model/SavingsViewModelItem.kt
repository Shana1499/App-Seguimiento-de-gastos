package com.example.appseguimientogastos.ui.view_model

import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavingsViewModelItem(itemsRepository: ItemsRepository) :
    ItemBaseViewModel(itemsRepository = itemsRepository) {

    // UI state
    private val _uiState = MutableStateFlow(MainState())//detecta cuando hay un cambio para repintar
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

}

