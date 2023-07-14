package com.example.appseguimientogastos.domain.model

import com.example.appseguimientogastos.data.model.ItemVO
import java.text.NumberFormat

data class Item(
    val id: Int = 0,
    val origin: String,
    val price: Double,
    val type: String,
    val month: String
) {
    fun toItemVO(): ItemVO {
        return ItemVO(id = id, origin = origin, price = price, type = type, month = month)
    }

    fun getFormattedPrice(): String =
        NumberFormat.getCurrencyInstance().format(price)

}

enum class Type(val typeName: String) {
    INCOMES("I"), EXPENSES("E"), SAVINGS("S")
}