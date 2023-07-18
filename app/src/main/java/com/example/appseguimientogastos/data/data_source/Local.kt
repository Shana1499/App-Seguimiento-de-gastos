package com.example.appseguimientogastos.data.data_source

import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type

interface Local {
    suspend fun addItem(item: Item)
    suspend fun getItemsListByType(type: Type): List<Item>
    suspend fun getItem(id: Int): Item
    suspend fun update(item: Item)
    suspend fun delete(item: Item)
}

