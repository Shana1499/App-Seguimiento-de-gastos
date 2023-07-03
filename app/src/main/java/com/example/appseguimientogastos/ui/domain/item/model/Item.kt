package com.example.appseguimientogastos.ui.domain.item.model

import com.example.appseguimientogastos.ui.data.item.local.ItemEntity
import java.text.NumberFormat

data class Item(
    val id: Int = 0,
    val origin: String,
    val price: Double,
    val type: String,
    val month: String
)

fun Item.toItemEntity(): ItemEntity =
    ItemEntity(id = id, origin = origin, price = price, type = type, month = month)

fun ItemEntity.toItem(): Item =
    Item(id = id, origin = origin, price = price, type = type, month = month)

fun Item.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(price)
