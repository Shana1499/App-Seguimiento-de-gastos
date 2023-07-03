package com.example.appseguimientogastos.ui.data.item

import com.example.appseguimientogastos.ui.data.item.local.ItemEntity
import com.example.appseguimientogastos.ui.data.item.model.ItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
class AppRepository @Inject constructor(
    private val itemDao: ItemDao
) {
    fun getAllData(): List<ItemEntity> {
        return itemDao.getAll()
    }

    fun insertItem(itemEntity: ItemEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            itemDao.insert(itemEntity)
        }
    }

    fun updateItem(itemEntity: ItemEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            itemDao.update(itemEntity)
        }
    }

    fun deleteItem(itemEntity: ItemEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            itemDao.delete(itemEntity)
        }
    }
}
*/