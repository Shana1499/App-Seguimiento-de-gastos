package com.example.appseguimientogastos

import android.app.Application
import com.example.appseguimientogastos.ui.data.item.model.AppDatabase
import com.example.appseguimientogastos.ui.view_model.AddViewModel
import com.example.appseguimientogastos.ui.view_model.ExpenseViewModel
import com.example.appseguimientogastos.ui.view_model.IncomeViewModel
import com.example.appseguimientogastos.ui.view_model.utils.BaseViewModel
import com.example.appseguimientogastos.ui.view_model.MainViewModel
import com.example.appseguimientogastos.ui.view_model.SavingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDI()
    }

    val appModule = module {
        // Define a singleton instance of MyDatabase
        single { AppDatabase.getInstance(androidContext()) }

        // Define a singleton instance of MyDao
        single { get<AppDatabase>().itemDao() }

        // Define a ViewModel instance of MyViewModel
        viewModel { BaseViewModel(get()) }
        viewModel { MainViewModel(get()) }
        viewModel { IncomeViewModel(get()) }
        viewModel { ExpenseViewModel(get()) }
        viewModel { SavingsViewModel(get()) }
        viewModel { AddViewModel(get()) }
    }


    private fun initializeDI() {
        startKoin {
            androidContext(this@MainApp)
            modules(appModule)

        }
    }
}