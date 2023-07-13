package com.example.appseguimientogastos.ui.view_model.utils

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.appseguimientogastos.ui.data.ItemsRepository
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.local.Type

//en lugar de pasar itemdao pasar repository


open class ItemBaseViewModel(
    protected val itemsRepository: ItemsRepository,
) : ViewModel() {
    private val coroutinesUtils = CoroutinesUtils()

    val myItems: LiveData<List<ItemVO>> = itemsRepository.readAllItems

    fun getItemByMonthList(
        currentMonth: MutableState<Month>,
        mutableList: MutableList<ItemVO>
    ): MutableList<ItemVO> {
        val newMutableList: MutableList<ItemVO> = mutableListOf()

        mutableList.forEach { item ->
            if (item.month == currentMonth.value.name) {
                newMutableList.add(item)
            }
        }

        return newMutableList


    }

    fun getItemByTypeList(type: Type): MutableList<ItemVO> {
        val itemList = mutableListOf<ItemVO>()
        coroutinesUtils.runMain {
            myItems.value?.forEach { item ->
                if (item.type == type.typeName) {
                    itemList.add(item)
                }
            }
        }
        return itemList
    }


    fun addItem(origin: String, price: String, month: String, type: Type) {
        val newitem = ItemVO(
            origin = origin,
            price = price.toDouble(),
            month = month,
            type = type.typeName
        )
        coroutinesUtils.runBG {
            itemsRepository.addItem(item = newitem)
        }

    }

    fun getItem(itemId: Int): LiveData<ItemVO> {
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
    }

}
