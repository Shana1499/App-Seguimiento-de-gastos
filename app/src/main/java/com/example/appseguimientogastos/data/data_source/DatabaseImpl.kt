package com.example.appseguimientogastos.data.data_source

import android.content.Context
import com.example.appseguimientogastos.data.data_source.constant.Constant
import com.example.appseguimientogastos.data.data_source.settings.Settings
import com.example.appseguimientogastos.data.model.ItemVO
import com.example.appseguimientogastos.data.model.ItemVOs
import com.example.appseguimientogastos.domain.model.Type

class DatabaseImpl(private val androidContext: Context, private val settings: Settings) : Database {

    override suspend fun addItem(item: ItemVO) {
        val currentItemsList = getItemsList()
        val lastId = currentItemsList.listItems.lastIndex
        var newId =0
        if(lastId>0){
            newId = lastId+1
        }
        item.id = newId
        currentItemsList.addNew(item)
        saveItemsList(currentItemsList)
    }

    override suspend fun getItemsListByType(type: Type): List<ItemVO> {
        val itemsList = getItemsList()
        return itemsList.listItems.filter { it.type == type.typeName }
    }

    override suspend fun getItemByID(id: Int): ItemVO? {
        val itemsList = getItemsList()
        return itemsList.listItems.find { it.id == id }
    }

    override suspend fun update(item: ItemVO) {
        val currentItemsList = getItemsList()
        val updatedItemsList = ItemVOs()
        updatedItemsList.listItems = currentItemsList.listItems.toMutableList().apply {
            val index = indexOfFirst { it.id == item.id }
            if (index != -1) {
                set(index, item)
            }
        }
        saveItemsList(updatedItemsList)
    }

    override suspend fun delete(itemId: Int) {
        val currentItemsList = getItemsList()
        val updatedItemsList = ItemVOs()
        updatedItemsList.listItems = currentItemsList.listItems.toMutableList().apply { removeIf { it.id == itemId } }
        saveItemsList(updatedItemsList)
    }

    private suspend fun getItemsList(): ItemVOs {
        val jsonItemsList = settings.getString(Constant.keyItemsList) ?: Constant.EMPTY_STRING
        return try {
            ItemVOs.fromJson(jsonItemsList)
        } catch (e: Throwable) {
            ItemVOs()
        }
    }

    private suspend fun saveItemsList(itemsList: ItemVOs) {
        val jsonItemsList = ItemVOs.toJson(itemsList)
        settings.setString(Constant.keyItemsList, jsonItemsList)
    }
}
