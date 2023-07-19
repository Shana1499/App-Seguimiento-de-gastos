package com.example.appseguimientogastos.ui.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.data.model.getCurrentMonth
import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModelItem(itemsRepository: ItemsRepository) :
    ItemBaseViewModel(itemsRepository = itemsRepository) {

    // UI state
    val uiState = MutableStateFlow(
        MainState()
    )

    init {
        updateMainState()
    }

    private fun updateMainState(currentMonth: MutableState<Month> = mutableStateOf(getCurrentMonth())) {
        coroutinesUtils.runMain {

            uiState.value = uiState.value.copy(
                currentMonth = currentMonth,
                isLoading = true
            )

            updateBaseState {
                coroutinesUtils.runBG {


                    val incomesListByMonth =
                        getItemByMonthList(uiState.value.currentMonth, baseState.value.incomesList)
                    val expensesListByMonth =
                        getItemByMonthList(uiState.value.currentMonth, baseState.value.expensesList)
                    val savingsListByMonth =
                        getItemByMonthList(uiState.value.currentMonth, baseState.value.savingsList)

                    val progressList = computeProgress()

                    val budget = computeBudget()

                    coroutinesUtils.runMain {

                        uiState.value = uiState.value.copy(
                            incomesListByMonth = incomesListByMonth,
                            expensesListByMonth = expensesListByMonth,
                            savingsListByMonth = savingsListByMonth,
                            progressList = progressList,
                            budget = budget,
                            isLoading = false
                        )
                    }
                }
            }
        }

    }

    fun getTotalIGA(): List<Double> {
        val totalIncomes = getItemByMonthList(
            uiState.value.currentMonth,
            baseState.value.incomesList
        ).sumOf { it.price }
        val totalExpenses = getItemByMonthList(
            uiState.value.currentMonth,
            baseState.value.expensesList
        ).sumOf { it.price }
        val totalSavings = getItemByMonthList(
            uiState.value.currentMonth,
            baseState.value.savingsList
        ).sumOf { it.price }

        return listOf(totalIncomes, totalExpenses, totalSavings)
    }

    private fun computeBudget(): Double {

        val income = getTotalIGA()[0]
        val expense = getTotalIGA()[1]
        val saving = getTotalIGA()[1]
        var budget = income
        budget = when {
            ((budget - expense - saving) <= 0.0) -> 0.0
            else -> budget - expense - saving
        }

        return budget
    }


    private fun computeProgress(
    ): List<Float> {
        val newProgressList = mutableListOf<Float>()

        val totalIncomes = getItemByMonthList(
            uiState.value.currentMonth,
            baseState.value.incomesList
        ).sumOf { it.price }
        val totalExpenses = getItemByMonthList(
            uiState.value.currentMonth,
            baseState.value.expensesList
        ).sumOf { it.price }
        val totalSavings = getItemByMonthList(
            uiState.value.currentMonth,
            baseState.value.savingsList
        ).sumOf { it.price }

        val grandTotal = totalIncomes + totalExpenses + totalSavings

        val incomePercent = (totalIncomes / grandTotal * 100.0).toFloat()
        newProgressList.add(incomePercent)

        val expensePercent = (totalExpenses / grandTotal * 100.0).toFloat()
        newProgressList.add(expensePercent)

        val savingsPercent = (totalSavings / grandTotal * 100.0).toFloat()
        newProgressList.add(savingsPercent)

        return newProgressList
    }

    fun onUpdateMonth(currentMonth: MutableState<Month>) {
        if (currentMonth == uiState.value.currentMonth) {
            updateMainState(currentMonth)
        }
    }

}
