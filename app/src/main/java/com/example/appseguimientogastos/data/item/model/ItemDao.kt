package com.example.appseguimientogastos.data.item.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appseguimientogastos.data.item.local.ItemEntity

@Dao
interface ItemDao {
    @Query("SELECT * from 'database' ORDER BY origin ASC")
    fun getAll(): List<ItemEntity>

    @Query("SELECT * from 'database' WHERE id = :id")
    fun getItem(id: Int): ItemEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ItemEntity)

    @Update
    suspend fun update(item: ItemEntity)

    @Delete
    suspend fun delete(item: ItemEntity)

}
