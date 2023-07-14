package com.example.appseguimientogastos.data

import com.example.appseguimientogastos.data.data_source.Local
import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type

class ItemsRepositoryImpl(private val local: Local) : ItemsRepository {
    override suspend fun addItem(item: Item) {
        local.addItem(item)
    }

    override suspend fun getIncomesList(): List<Item> {
        return local.getItemsListByType(Type.INCOMES)
    }

    override suspend fun getExpensesList(): List<Item> {
        return local.getItemsListByType(Type.EXPENSES)
    }

    override suspend fun getSavingsList(): List<Item> {
        return local.getItemsListByType(Type.EXPENSES)
    }

}