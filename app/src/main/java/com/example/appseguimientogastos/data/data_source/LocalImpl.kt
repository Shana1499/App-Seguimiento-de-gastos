package com.example.appseguimientogastos.data.data_source

import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type

class LocalImpl(private val database: Database) : Local {
    override suspend fun addItem(item: Item) {
        database.addItem(item.toItemVO())
    }

    override suspend fun getItemsListByType(type: Type): List<Item> {
        return database.getItemsListByType(type = type)
    }

    override suspend fun getItem(id: Int): Item {
        return database.getItemByID(id = id)
    }

    override suspend fun update(item: Item) {
        database.update(item = item.toItemVO())
    }

    override suspend fun delete(item: Item) {
        database.delete(item = item.toItemVO())
    }
}