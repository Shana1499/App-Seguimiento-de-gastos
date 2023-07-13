package com.example.appseguimientogastos.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appseguimientogastos.ui.data.ItemsRepository
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.item.model.ItemDao
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import com.example.appseguimientogastos.ui.view_model.utils.CoroutinesUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExpenseViewModelItem(itemsRepository: ItemsRepository) :
    ItemBaseViewModel(itemsRepository = itemsRepository) {

    private val coroutinesUtils = CoroutinesUtils()

    // UI state
    private val _uiState = MutableStateFlow(MainState())//detecta cuando hay un cambio para repintar
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    var expenseList = getItemByTypeList(Type.EXPENSES)


}

