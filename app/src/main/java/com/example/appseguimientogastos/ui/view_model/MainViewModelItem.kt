package com.example.appseguimientogastos.ui.view_model

import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModelItem(itemsRepository: ItemsRepository) :
    ItemBaseViewModel(itemsRepository = itemsRepository) {

    // UI state
    val uiState = MutableStateFlow(
        MainState()
    )
    /*init {
        coroutinesUtils.runBG {
            // por cada lista un get
            val incomesList = itemsRepository.getIncomesList()
            val expensesList = itemsRepository.getExpensesList()
            val savingsList = itemsRepository.getSavingsList()

            val incomesListByMonth = getItemByMonthList(uiState.value.currentMonth, incomesList)
            val expensesListByMonth = getItemByMonthList(uiState.value.currentMonth, expensesList)
            val savingsListByMonth = getItemByMonthList(uiState.value.currentMonth,savingsList)

           // val progressList = computeProgress()

            coroutinesUtils.runMain {
                baseState.value = baseState.value.copy(
                    // poner las listas
                    incomesList = incomesList,
                    expensesList = expensesList,
                    savingsList = savingsList,
                    isLoading = false
                )

                uiState.value= uiState.value.copy(
                    incomesListByMonth=incomesListByMonth,
                    expensesListByMonth = expensesListByMonth,
                    savingsListByMonth = savingsListByMonth,
                   // progressList=progressList,
                    isLoading = false
                )
            }
        }
    }*/


// mover al base state
    /* val incomesList = getItemByTypeList(Type.INCOMES)
    val expensesList = getItemByTypeList(Type.EXPENSES)
    val savingsList = getItemByTypeList(Type.SAVINGS) */



    fun computeProgress(
    ): List<Float> {
        val newProgressList = mutableListOf<Float>()

        val totalIncomes = getItemByMonthList(uiState.value.currentMonth, baseState.value.incomesList).sumOf { it.price }
        val totalExpenses = getItemByMonthList(uiState.value.currentMonth, baseState.value.expensesList).sumOf { it.price }
        val totalSavings = getItemByMonthList(uiState.value.currentMonth, baseState.value.savingsList).sumOf { it.price }

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
