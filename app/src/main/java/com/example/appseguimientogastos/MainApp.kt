package com.example.appseguimientogastos

import android.app.Application
import com.example.appseguimientogastos.ui.data.ItemsRepository
import com.example.appseguimientogastos.ui.data.item.model.AppDatabase
import com.example.appseguimientogastos.ui.view_model.AddViewModelItem
import com.example.appseguimientogastos.ui.view_model.ExpenseViewModelItem
import com.example.appseguimientogastos.ui.view_model.IncomeViewModelItem
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import com.example.appseguimientogastos.ui.view_model.MainViewModelItem
import com.example.appseguimientogastos.ui.view_model.SavingsViewModelItem
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
        // Define a singleton instance of AppDatabase
        single { AppDatabase.getInstance(androidContext()) }

        // Define a singleton instance of ItemDao
        single { get<AppDatabase>().itemDao() }

        // Define a singleton instance of ItemsRepository
        single { ItemsRepository(get()) }

        // Define ViewModel instances
        viewModel { ItemBaseViewModel(get()) }
        viewModel { MainViewModelItem(get()) }
        viewModel { IncomeViewModelItem(get()) }
        viewModel { ExpenseViewModelItem(get()) }
        viewModel { SavingsViewModelItem(get()) }
        viewModel { AddViewModelItem(get()) }
    }


    private fun initializeDI() {
        startKoin {
            androidContext(this@MainApp)
            modules(appModule)
        }
    }
}
