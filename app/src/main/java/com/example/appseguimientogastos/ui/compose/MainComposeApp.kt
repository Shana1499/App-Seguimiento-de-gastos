package com.example.appseguimientogastos.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.appseguimientogastos.ui.compose.MainScreen.MainAppBar
import com.example.appseguimientogastos.ui.compose.MainScreen.MainScreen
import com.example.compose.AppSeguimientoGastosTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposeApp(
    modifier: Modifier = Modifier
) {

    AppSeguimientoGastosTheme {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Scaffold(topBar = {
                MainAppBar()

            }) { innerPadding ->
                MainScreen(modifier = modifier.padding(innerPadding))

            }


        }
    }
}


