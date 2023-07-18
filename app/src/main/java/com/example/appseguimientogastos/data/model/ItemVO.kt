package com.example.appseguimientogastos.data.model

import com.example.appseguimientogastos.data.data_source.constant.Constant
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

@Serializable
class ItemVO(
    var id: Int = 0,
    val origin: String,
    val price: Double,
    val type: String,
    val month: String
) {

    fun getReadableData(): String =
        "- id =$id, origin=$origin, price=$price, type=$type"
    override fun toString(): String = "ItemVO(id =$id, origin=$origin, price=$price, type=$type)"

    companion object {
        fun toJson(item: ItemVO): String = Constant.jsonCustom.encodeToString(item)
        fun fromJson(json: String): ItemVO = Constant.jsonCustom.decodeFromString(json)


    }
}
