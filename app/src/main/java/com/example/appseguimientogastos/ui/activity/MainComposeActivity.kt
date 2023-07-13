package com.example.appseguimientogastos.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComposeApp()
        }

    }
}