package com.example.appseguimientogastos.ui.data.item.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat
enum class Type(val typeName: String) {
    INCOMES("I"), EXPENSES("E"), SAVINGS("S")
}

const val databaseName = "ASGdatabase"
@Entity(tableName = databaseName)
data class ItemVO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "origin")
    val origin: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "month")
    val month: String

)

fun ItemVO.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(price)
