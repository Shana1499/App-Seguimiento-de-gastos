package com.example.appseguimientogastos.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appseguimientogastos")
data class AppSeguimientoGastos(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "origin") val name: String,
    @ColumnInfo(name = "money") val money: Double,
    @ColumnInfo(name = "month") val month: String,
    @ColumnInfo(name = "type") val type: String,
)

