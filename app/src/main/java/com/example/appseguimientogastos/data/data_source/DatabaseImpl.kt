package com.example.appseguimientogastos.data.data_source

import android.content.Context
import com.example.appseguimientogastos.data.data_source.settings.Settings
import com.example.appseguimientogastos.data.model.ItemVO
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type

class DatabaseImpl(private val androidContext: Context, private val settings: Settings):Database {
    override suspend fun addItem(item: ItemVO) {
        TODO("Not yet implemented")
    }

    override suspend fun getItemsListByType(type: Type): List<Item> {
        TODO("Not yet implemented")
    }
}