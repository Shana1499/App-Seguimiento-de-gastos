package com.example.appseguimientogastos.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.appseguimientogastos.data.data_source.Database
import com.example.appseguimientogastos.data.data_source.DatabaseImpl
import com.example.appseguimientogastos.data.data_source.constant.Constant
import com.example.appseguimientogastos.data.data_source.constant.buildType
import com.example.appseguimientogastos.data.data_source.settings.AndroidSettings
import com.example.appseguimientogastos.data.data_source.settings.Settings
import com.example.appseguimientogastos.data.di.DIData
import com.example.appseguimientogastos.domain.di.DIDomain
import org.koin.androidx.compose.BuildConfig
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

class DIPlatform constructor(private val context: Context) {
    fun createPlatformModule() = module {
        single<Context> { context }
        single<Settings> {
            AndroidSettings(
                EncryptedSharedPreferences.create(
                    get(),
                    Constant.preferencesName(buildType(BuildConfig.BUILD_TYPE)),
                    MasterKey.Builder(get()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            )
        }
        single<Database> { DatabaseImpl(get(), get()) }
    }
}

class InitializationDI {
    fun loadModules(appModule: Module, context: Context) {
        loadKoinModules(
            listOf(
                DIPlatform(context = context).createPlatformModule(),
                DIDomain().createDomainModule(),
                DIData().createDataModule(),
                appModule,
            )
        )
    }
}
