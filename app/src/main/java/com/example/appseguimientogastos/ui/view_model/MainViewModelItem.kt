package com.example.appseguimientogastos.ui.view_model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appseguimientogastos.ui.data.ItemsRepository
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.item.model.ItemDao
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import com.example.appseguimientogastos.ui.view_model.utils.CoroutinesUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModelItem(itemsRepository: ItemsRepository) :
    ItemBaseViewModel(itemsRepository = itemsRepository) {

    private val coroutinesUtils = CoroutinesUtils()

    // UI state
    private val _uiState = MutableStateFlow(MainState())//detecta cuando hay un cambio para repintar
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    val incomesList = getItemByTypeList(Type.INCOMES)
    val expensesList = getItemByTypeList(Type.EXPENSES)
    val savingsList = getItemByTypeList(Type.SAVINGS)

    fun computeProgress(
        currentMonth: MutableState<Month>,
    ): List<Float> {


        val newProgressList = mutableListOf<Float>()

        val totalIncomes = getItemByMonthList(currentMonth, incomesList).sumOf { it.price }
        val totalExpenses = getItemByMonthList(currentMonth, expensesList).sumOf { it.price }
        val totalSavings = getItemByMonthList(currentMonth, savingsList).sumOf { it.price }

        val grandTotal = totalIncomes + totalExpenses + totalSavings

        val incomePercent = (totalIncomes / grandTotal * 100.0).toFloat()
        newProgressList.add(incomePercent)

        val expensePercent = (totalExpenses / grandTotal * 100.0).toFloat()
        newProgressList.add(expensePercent)

        val savingsPercent = (totalSavings / grandTotal * 100.0).toFloat()
        newProgressList.add(savingsPercent)

        return newProgressList
    }


}
