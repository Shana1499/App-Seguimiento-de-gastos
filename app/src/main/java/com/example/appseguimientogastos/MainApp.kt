package com.example.appseguimientogastos

import android.app.Application
import com.example.appseguimientogastos.di.DIApp
import com.example.appseguimientogastos.di.InitializationDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDI()
    }

    private fun initializeDI() {
        startKoin {
            androidContext(this@MainApp)
            InitializationDI().loadModules(DIApp().createAppModule(this@MainApp),this@MainApp)
        }
    }
}
