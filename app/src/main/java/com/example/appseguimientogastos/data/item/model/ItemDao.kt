package com.example.appseguimientogastos.data.item.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appseguimientogastos.data.item.local.ItemVO

@Dao
interface ItemDao {
    @Query("SELECT * from 'database' ORDER BY origin ASC")
    fun getAll(): List<ItemVO>

    @Query("SELECT * from 'database' WHERE id = :id")
    fun getItem(id: Int): ItemVO

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ItemVO)

    @Update
    suspend fun update(item: ItemVO)

    @Delete
    suspend fun delete(item: ItemVO)

}
