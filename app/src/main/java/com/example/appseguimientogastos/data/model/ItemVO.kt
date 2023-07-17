package com.example.appseguimientogastos.data.model

import com.example.appseguimientogastos.domain.model.Item


data class ItemVO(
    val id: Int = 0,
    val origin: String,
    val price: Double,
    val type: String,
    val month: String


)

fun ItemVO.toModel(): Item {
    return Item(
        id = id,
        origin = origin,
        price = price,
        type = type,
        month = month
    )
}
