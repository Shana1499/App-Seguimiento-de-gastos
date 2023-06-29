package com.example.appseguimientogastos.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity
data class Item(
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

fun Item.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(price)
