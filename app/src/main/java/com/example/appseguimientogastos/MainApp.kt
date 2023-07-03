package com.example.appseguimientogastos

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp :Application(){
    override fun onCreate() {
        super.onCreate()
        initializeDI()
    }

    private fun initializeDI() {
        startKoin {
            androidContext(this@MainApp)
           // InitializationDI().loadModules(DIApp().createAppModule(this@MainApp))
        }
    }
}