package com.example.appseguimientogastos.ui.view_model.utils

import android.content.ClipData
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appseguimientogastos.ui.data.Month
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.item.model.ItemDao

open class BaseViewModel(private val itemDao: ItemDao) : ViewModel() {

    val myItems: LiveData<List<ItemVO>>  = itemDao.getAll()

    private val coroutinesUtils = CoroutinesUtils()

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
            itemDao.insert(newitem)
        }

    }

    fun getItem(itemId: Int): LiveData<ItemVO> {
        return itemDao.getItem(id = itemId)
    }

    fun updateItem(item: ItemVO) {
        coroutinesUtils.runBG {
            itemDao.update(item)
        }
    }

    fun deleteItem(item: ItemVO) {
        coroutinesUtils.runBG {
            itemDao.delete(item)
        }
    }

}

class BaseViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            return BaseViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
