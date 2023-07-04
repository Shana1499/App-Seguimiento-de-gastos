package com.example.appseguimientogastos.ui.data.item.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appseguimientogastos.ui.data.item.local.ItemVO

@Dao
interface ItemDao {
    @Query("SELECT * from asgdatabase ORDER BY origin ASC")
    fun getAll(): LiveData<List<ItemVO>>

    @Query("SELECT * from asgdatabase WHERE id = :id")
    fun getItem(id: Int): LiveData<ItemVO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ItemVO)

    @Update
    suspend fun update(item: ItemVO)

    @Delete
    suspend fun delete(item: ItemVO)

}
