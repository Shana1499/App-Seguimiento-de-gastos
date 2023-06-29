package com.example.appseguimientogastos.data

import android.content.ClipData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppSeguimientoGastosDao {
    @Insert
    suspend fun insertAll(parks: List<AppSeguimientoGastos>)
    @Query("SELECT * FROM appseguimientogastos")
    suspend fun getAll(): List<AppSeguimientoGastos>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item:AppSeguimientoGastos)

    @Update
    suspend fun update(item: AppSeguimientoGastos)

    @Delete
    suspend fun delete(item: AppSeguimientoGastos)

    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<AppSeguimientoGastos>

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<AppSeguimientoGastos>>
}
