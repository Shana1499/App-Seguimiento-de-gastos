package com.example.appseguimientogastos.domain.item.model

import com.example.appseguimientogastos.data.item.local.ItemEntity

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