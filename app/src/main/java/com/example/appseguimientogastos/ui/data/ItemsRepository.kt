package com.example.appseguimientogastos.ui.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.model.AppDatabase
import com.example.appseguimientogastos.ui.data.item.model.ItemDao

class ItemsRepository(application: Application) {
    private var itemDao: ItemDao

    init {
        val database = AppDatabase.getDatabase(application)
        itemDao = database.itemDao()
    }

    val readAllItems = itemDao.getAll()
    suspend fun addItem(item: ItemVO) {
        itemDao.insert(item)
    }

    suspend fun deleteItem(item: ItemVO) {
        itemDao.delete(item)
    }

    fun getItem(id: Int): LiveData<ItemVO> {
        return itemDao.getItem(id = id)
    }

    suspend fun update(item: ItemVO) {

        itemDao.update(item)

    }

    suspend fun delete(item: ItemVO) {
        itemDao.delete(item)

    }

    init {
        val database = AppDatabase.getDatabase(application)
        itemDao = database.itemDao()
    }
}