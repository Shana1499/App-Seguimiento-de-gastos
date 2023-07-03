package com.example.appseguimientogastos.di

import android.content.Context
import androidx.room.Room
import com.example.appseguimientogastos.data.item.model.AppDatabase
import com.example.appseguimientogastos.data.item.model.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providesAppRoomDatabase(@ApplicationContext app: Context): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "database"
        ).build()

    @Provides
    @Singleton
    fun providesItemDao(appDatabase: AppDatabase): ItemDao =appDatabase.itemDao()

}

