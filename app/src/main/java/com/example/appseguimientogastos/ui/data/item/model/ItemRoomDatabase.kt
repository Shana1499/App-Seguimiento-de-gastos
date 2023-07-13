package com.example.appseguimientogastos.ui.data.item.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.local.databaseName

@Database(entities = [ItemVO::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                databaseName
            ).build()
        }
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, databaseName
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}