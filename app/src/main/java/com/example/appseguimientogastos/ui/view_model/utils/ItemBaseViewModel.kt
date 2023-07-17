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
    val coroutinesUtils = CoroutinesUtils()


    // UI state
    val baseState = MutableStateFlow(
        BaseState()
    )


    fun getItemByMonthList(
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

    fun addItem(origin: String, price: String, month: String, type: Type) {
        val newitem = Item(
            origin = origin,
            price = price.toDouble(),
            month = month,
            type = type.typeName
        )
        coroutinesUtils.runBG {
            itemsRepository.addItem(item = newitem)
        }

    }

    /*fun getItem(itemId: Int): ItemVO {
        return itemsRepository.getItem(id = itemId)
    }

    fun updateItem(item: ItemVO) {
        coroutinesUtils.runBG {
            itemsRepository.update(item)
        }
    }

    fun deleteItem(item: ItemVO) {
        coroutinesUtils.runBG {
            itemsRepository.delete(item)
        }
    }*/

}
