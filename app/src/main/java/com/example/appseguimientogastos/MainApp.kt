package com.example.appseguimientogastos

import android.app.Application
import android.content.Context
import com.example.appseguimientogastos.data.ItemsRepositoryImpl
import com.example.appseguimientogastos.data.data_source.Database
import com.example.appseguimientogastos.data.data_source.DatabaseImpl
import com.example.appseguimientogastos.data.data_source.Local
import com.example.appseguimientogastos.data.data_source.LocalImpl
import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.ui.view_model.AddViewModelItem
import com.example.appseguimientogastos.ui.view_model.ExpenseViewModelItem
import com.example.appseguimientogastos.ui.view_model.IncomeViewModelItem
import com.example.appseguimientogastos.ui.view_model.MainViewModelItem
import com.example.appseguimientogastos.ui.view_model.SavingsViewModelItem
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
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

        single<Context> { this@MainApp }

        single<Database> { DatabaseImpl(get()) }

        single<Local> { LocalImpl(get()) }
        // Define a singleton instance of ItemsRepository
        single<ItemsRepository> { ItemsRepositoryImpl(get()) }


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
