package com.example.appseguimientogastos.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.model.ItemDao
import com.example.appseguimientogastos.ui.view_model.utils.CoroutinesUtils

class BaseViewModel(private val itemDao: ItemDao) : ViewModel() {

    val myItems = itemDao.getAll()

    private val coroutinesUtils = CoroutinesUtils()

    fun addItem(item: ItemVO) {
        coroutinesUtils.runBG {
            itemDao.insert(item)
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
