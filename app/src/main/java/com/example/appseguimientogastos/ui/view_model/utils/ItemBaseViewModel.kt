package com.example.appseguimientogastos.ui.view_model.utils

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.appseguimientogastos.data.model.Month
import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type
import com.example.appseguimientogastos.ui.view_model.BaseState
import kotlinx.coroutines.flow.MutableStateFlow

//en lugar de pasar itemdao pasar repository


open class ItemBaseViewModel(
    protected val itemsRepository: ItemsRepository,
) : ViewModel() {
    protected val coroutinesUtils = CoroutinesUtils()


    // UI state
    val baseState = MutableStateFlow(
        BaseState()
    )


    protected fun getItemByMonthList(
        currentMonth: MutableState<Month>,
        mutableList: List<Item>
    ): MutableList<Item> {
        val newMutableList: MutableList<Item> = mutableListOf()

        mutableList.forEach { item ->
            if (item.month == currentMonth.value.name) {
                newMutableList.add(item)
            }
        }

        return newMutableList


    }

    fun onAddItem(
        origin: String,
        price: String,
        month: String,
        type: Type,
        onAddItemCompleted:()->Unit
    ) {
        val newItem = Item(
            origin = origin,
            price = price.toDoubleOrNull() ?: 0.0,
            month = month,
            type = type.typeName
        )
        coroutinesUtils.runBG {
            itemsRepository.addItem(newItem)
            // actualizar baseState
            updateBaseState(onAddItemCompleted=onAddItemCompleted )


        }


    }

    protected fun updateBaseState(onAddItemCompleted: () -> Unit) {
        coroutinesUtils.runBG {
            // por cada lista un get
            val incomesList = itemsRepository.getIncomesList()
            val expensesList = itemsRepository.getExpensesList()
            val savingsList = itemsRepository.getSavingsList()

            coroutinesUtils.runMain {
                baseState.value = baseState.value.copy(
                    // poner las listas
                    incomesList = incomesList,
                    expensesList = expensesList,
                    savingsList = savingsList,
                    isLoading = false
                )
                onAddItemCompleted()

            }
        }
    }

    protected fun getItem(itemId: Int): Item? {
        var item: Item? = null
        coroutinesUtils.runBG {
            item = itemsRepository.getItem(id = itemId)
        }
        return item

    }

    protected fun updateItem(item: Item) {
        coroutinesUtils.runBG {
            itemsRepository.update(item)
        }
    }

    protected fun deleteItem(item: Item) {
        coroutinesUtils.runBG {
            itemsRepository.delete(item.id)
        }
    }

}
