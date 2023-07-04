package com.example.appseguimientogastos

import android.app.Application
import com.example.appseguimientogastos.ui.data.item.model.AppDatabase
import com.example.appseguimientogastos.ui.view_model.MainViewModel
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
        viewModel { MainViewModel(get()) }
    }


    private fun initializeDI() {
        startKoin {
            androidContext(this@MainApp)
            modules(appModule) 

        }
    }
}