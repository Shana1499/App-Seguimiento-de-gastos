package com.example.appseguimientogastos.data.data_source

import com.example.appseguimientogastos.data.model.ItemVO
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type

interface Database {
    suspend fun addItem(item: ItemVO)
    suspend fun getItemsListByType(type: Type):List<Item>

}

