package com.example.appseguimientogastos.domain

import com.example.appseguimientogastos.domain.model.Item


interface ItemsRepository {
    suspend fun addItem(item: Item)
    suspend fun getIncomesList(): List<Item>
    suspend fun getExpensesList(): List<Item>
    suspend fun getSavingsList(): List<Item>
}