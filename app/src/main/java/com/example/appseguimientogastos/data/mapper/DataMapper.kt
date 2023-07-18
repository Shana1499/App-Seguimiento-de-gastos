package com.example.appseguimientogastos.data.mapper

import com.example.appseguimientogastos.data.model.ItemVO
import com.example.appseguimientogastos.domain.model.Item

fun ItemVO.toModel(): Item {
    return Item(
        id = id,
        origin = origin,
        price = price,
        type = type,
        month = month
    )
}

fun Item.toVO(): ItemVO {
    return ItemVO(id = id, origin = origin, price = price, type = type, month = month)
}
