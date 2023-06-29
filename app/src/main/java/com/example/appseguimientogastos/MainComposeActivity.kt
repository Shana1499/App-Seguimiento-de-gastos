package com.example.appseguimientogastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appseguimientogastos.data.AppDatabase
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComposeApp()
        }
        GlobalScope.launch {
            AppDatabase.getDatabase(applicationContext).californiaParkDao().getAll()
        }
    }
}



