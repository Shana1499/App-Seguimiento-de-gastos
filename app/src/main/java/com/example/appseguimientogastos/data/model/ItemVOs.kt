package com.example.appseguimientogastos.data.model

import android.content.ContentValues.TAG
import android.util.Log
import com.example.appseguimientogastos.data.data_source.constant.Constant
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

@Serializable
class ItemVOs
    (
    var listItems: List<ItemVO> = emptyList(),
) {

    fun addNew(item: ItemVO) {
        var newList = mutableListOf<ItemVO>()
        newList.add(item)
        newList.addAll(listItems)
        listItems = newList.toList()
        Log.d(TAG, "addNew: $listItems")
    }

    fun getReadableData(): String = listItems.joinToString("\n\n\n") { it.getReadableData() }

    override fun toString(): String = "ItemVOs:(listItems=$listItems)"

    companion object {
        fun toJson(items: ItemVOs): String = Constant.jsonCustom.encodeToString(items)

        fun fromJson(jsonItemsList: String): ItemVOs =
            Constant.jsonCustom.decodeFromString(jsonItemsList)

    }
}