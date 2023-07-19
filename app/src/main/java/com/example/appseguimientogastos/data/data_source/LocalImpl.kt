package com.example.appseguimientogastos.data.data_source

import com.example.appseguimientogastos.data.model.ItemVO
import com.example.appseguimientogastos.domain.model.Type

class LocalImpl(private val database: Database) : Local {
    override suspend fun addItem(item: ItemVO) {
        database.addItem(item)
    }

    override suspend fun getItemsListByType(type: Type): List<ItemVO> {
        return database.getItemsListByType(type = type)
    }

    override suspend fun getItem(id: Int): ItemVO? {
        return database.getItemByID(id = id)
    }

    override suspend fun update(item: ItemVO) {
        database.update(item = item)
    }

    override suspend fun delete(itemId: Int) {
        database.delete( itemId = itemId)
    }
}