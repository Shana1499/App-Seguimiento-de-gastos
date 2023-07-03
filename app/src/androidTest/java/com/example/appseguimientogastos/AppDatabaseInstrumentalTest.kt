package com.example.appseguimientogastos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appseguimientogastos.data.item.model.AppDatabase
import com.example.appseguimientogastos.data.item.local.Item
import com.example.appseguimientogastos.data.item.model.ItemDao
import com.example.appseguimientogastos.data.item.local.Type
import com.example.appseguimientogastos.data.monthList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AppDatabaseInstrumentalTest {
    private lateinit var itemDao: ItemDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        itemDao = db.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeItemAndReadAllList() {
        val item = Item(
            origin = "A",
            price = 4.0,
            type = Type.INCOMES.typeName,
            month = monthList[7 - 1].name
        )

        CoroutineScope(Dispatchers.IO).launch {
            itemDao.insert(item = item)
        }

        val data = itemDao.getAll()

        CoroutineScope(Dispatchers.IO).launch {
            itemDao.delete(item = item)
        }

        println(data)

        assertEquals(data[0].origin, "A")

    }
}