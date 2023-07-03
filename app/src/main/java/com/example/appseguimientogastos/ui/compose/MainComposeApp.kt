package com.example.appseguimientogastos.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.ui.Main
import com.example.appseguimientogastos.ui.MainComposeNavHost
import com.example.appseguimientogastos.ui.data.getCurrentMonth
import com.example.appseguimientogastos.ui.navigateSingleTopTo
import com.example.appseguimientogastos.ui.tabRowScreens
import com.example.compose.AppSeguimientoGastosTheme

@Composable
fun MainComposeApp(
    modifier: Modifier = Modifier,

    ) {
    AppSeguimientoGastosTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        // list of nav screens + icons
        val currentMonth = remember { mutableStateOf(getCurrentMonth()) }

        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            tabRowScreens.find { it.route == currentDestination?.route } ?: Main


        Surface(
            modifier = modifier.fillMaxSize()
        ) {

            ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
                CustoNavigationDrawer(
                    allScreens = tabRowScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen,
                    drawerState = drawerState,
                    scope = scope,
                )
            }) {
                Scaffold(
                    topBar = {
                        MainComposeAppBar(
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                ) { innerPadding ->


                    MainComposeNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        currentMonth = currentMonth,
                        allScreens = tabRowScreens,


                        )
                }
            }
        }

    }

}
