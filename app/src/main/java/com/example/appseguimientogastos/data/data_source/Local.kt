package com.example.appseguimientogastos.data.data_source

import com.example.appseguimientogastos.data.model.ItemVO
import com.example.appseguimientogastos.domain.model.Type

interface Local {
    suspend fun addItem(item: ItemVO)
    suspend fun getItemsListByType(type: Type): List<ItemVO>
    suspend fun getItem(id: Int): ItemVO?
    suspend fun update(item: ItemVO)
    suspend fun delete(itemId: Int)
}

