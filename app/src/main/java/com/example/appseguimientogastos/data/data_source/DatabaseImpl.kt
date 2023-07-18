package com.example.appseguimientogastos.data.data_source

import android.content.Context
import com.example.appseguimientogastos.data.data_source.settings.Settings
import com.example.appseguimientogastos.data.model.ItemVO
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseImpl(private val androidContext: Context, private val settings: Settings): Database {

    private val keyItemsList = "KEY_ITEMS_LIST"

    override suspend fun addItem(item: ItemVO) {
        val currentItemsList = getItemsList()
        val updatedItemsList = currentItemsList.toMutableList().apply { add(item) }
        saveItemsList(updatedItemsList)
    }

    override suspend fun getItemsListByType(type: Type): List<Item> {
        val itemsList = getItemsList()
        return itemsList.filter { it.type == type.typeName }.map { it.toModel() }
    }

    override suspend fun getItemByID(id: Int): Item {
        val itemsList = getItemsList()
        return itemsList.find { it.id == id }?.toModel() ?: throw NoSuchElementException("Item with ID $id not found.")
    }

    override suspend fun update(item: ItemVO) {
        val currentItemsList = getItemsList()
        val updatedItemsList = currentItemsList.toMutableList().apply {
            val index = indexOfFirst { it.id == item.id }
            if (index != -1) {
                set(index, item)
            }
        }
        saveItemsList(updatedItemsList)
    }

    override suspend fun delete(item: ItemVO) {
        val currentItemsList = getItemsList()
        val updatedItemsList = currentItemsList.toMutableList().apply { removeIf { it.id == item.id } }
        saveItemsList(updatedItemsList)
    }

    private suspend fun getItemsList(): List<ItemVO> = withContext(Dispatchers.IO) {
        val jsonItemsList = settings.getString(keyItemsList) ?: "[]"
        return@withContext try {
            ItemVO.fromJsonList(jsonItemsList)
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun saveItemsList(itemsList: List<ItemVO>) = withContext(Dispatchers.IO) {
        val jsonItemsList = ItemVO.toJsonList(itemsList)
        settings.setString(keyItemsList, jsonItemsList)
    }
}
