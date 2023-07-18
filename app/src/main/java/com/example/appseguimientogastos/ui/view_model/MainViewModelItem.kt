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

    init {
        updateMainState()
    }

    fun updateMainState() {
        coroutinesUtils.runMain {

            uiState.value = uiState.value.copy(
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

                    coroutinesUtils.runMain {

                        uiState.value = uiState.value.copy(
                            incomesListByMonth = incomesListByMonth,
                            expensesListByMonth = expensesListByMonth,
                            savingsListByMonth = savingsListByMonth,
                            progressList = progressList,
                            isLoading = false
                        )
                    }
                }
            }
        }

    }
// mover al base state
    /* val incomesList = getItemByTypeList(Type.INCOMES)
    val expensesList = getItemByTypeList(Type.EXPENSES)
    val savingsList = getItemByTypeList(Type.SAVINGS) */


    fun computeProgress(
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


}
