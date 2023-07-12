package com.example.appseguimientogastos.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.item.model.ItemDao
import com.example.appseguimientogastos.ui.view_model.utils.BaseViewModel
import com.example.appseguimientogastos.ui.view_model.utils.CoroutinesUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExpenseViewModel(itemDao: ItemDao) : BaseViewModel(itemDao) {

    private val coroutinesUtils = CoroutinesUtils()

    // UI state
    private val _uiState = MutableStateFlow(MainState())//detecta cuando hay un cambio para repintar
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    var expenseList = getItemByTypeList(Type.EXPENSES)


}

class ExpenseViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            return ExpenseViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}