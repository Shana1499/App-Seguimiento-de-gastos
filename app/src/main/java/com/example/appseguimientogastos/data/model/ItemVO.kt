package com.example.appseguimientogastos.data.model

import com.example.appseguimientogastos.domain.model.Item
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ItemVO(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("origin")
    val origin: String,
    @SerialName("price")
    val price: Double,
    @SerialName("type")
    val type: String,
    @SerialName("month")
    val month: String
) {
    fun toModel(): Item {
        return Item(
            id = id,
            origin = origin,
            price = price,
            type = type,
            month = month
        )
    }

    companion object {
        fun toJson(): String = Json.encodeToString(this)
        fun fromJson(json: String): ItemVO = Json.decodeFromString(json)
        fun toJsonList(items: List<ItemVO>): String {
            return Json.encodeToString(items)
        }
        fun fromJsonList(json: String): List<ItemVO> {
            return Json.decodeFromString(json)
        }

    }
}
