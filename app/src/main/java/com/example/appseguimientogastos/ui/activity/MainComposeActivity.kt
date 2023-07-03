package com.example.appseguimientogastos.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.appseguimientogastos.ui.compose.MainComposeApp

class MainComposeActivity : BaseComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, MainComposeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComposeApp()
        }
    }
}