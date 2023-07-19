package com.example.appseguimientogastos.data.di

import com.example.appseguimientogastos.data.ItemsRepositoryImpl
import com.example.appseguimientogastos.data.data_source.Local
import com.example.appseguimientogastos.data.data_source.LocalImpl
import com.example.appseguimientogastos.domain.ItemsRepository
import org.koin.dsl.module

class DIData {
    fun createDataModule() = module {
        single<Local> { LocalImpl(get()) }

        single<ItemsRepository> {
            ItemsRepositoryImpl(
                local = get(),
            )
        }
    }
}