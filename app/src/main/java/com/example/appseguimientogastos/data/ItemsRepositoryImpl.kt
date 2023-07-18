package com.example.appseguimientogastos.data

import com.example.appseguimientogastos.data.data_source.Local
import com.example.appseguimientogastos.data.mapper.toModel
import com.example.appseguimientogastos.data.mapper.toVO
import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type

class ItemsRepositoryImpl(private val local: Local) : ItemsRepository {
    override suspend fun addItem(item: Item) {
        local.addItem(item.toVO())
    }

    override suspend fun getIncomesList(): List<Item> =
        local.getItemsListByType(Type.INCOMES).map { it.toModel() }

    override suspend fun getExpensesList(): List<Item> =
        local.getItemsListByType(Type.EXPENSES).map { it.toModel() }

    override suspend fun getSavingsList(): List<Item> =
        local.getItemsListByType(Type.EXPENSES).map { it.toModel() }

    override suspend fun getItem(id: Int): Item? = local.getItem(id)?.toModel()

    override suspend fun update(item: Item) {
        local.update(item.toVO())
    }

    override suspend fun delete(itemId: Int) {
        local.delete(itemId)
    }

}