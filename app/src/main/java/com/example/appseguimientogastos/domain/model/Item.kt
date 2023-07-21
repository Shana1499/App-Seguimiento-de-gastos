package com.example.appseguimientogastos.domain.model

import kotlinx.serialization.Serializable
import java.text.NumberFormat

@Serializable
data class Item(
    val id: Int = 0,
    val origin: String,
    val price: Double,
    val type: String,
    val month: String
) {
    fun getFormattedPrice(): String =
        NumberFormat.getCurrencyInstance().format(price)

    override fun toString(): String = "origin: $origin, amount: ${getFormattedPrice()}"


}

enum class Type(val typeName: String) {
    INCOMES("I"), EXPENSES("E"), SAVINGS("S")
}