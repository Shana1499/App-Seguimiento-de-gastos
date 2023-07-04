package com.example.appseguimientogastos.ui.compose.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.appseguimientogastos.ui.navigation.MainComposeDestination
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.navigation.tabRowScreens
import kotlinx.coroutines.CoroutineScope

@Composable
fun CommonUI(
    navController: NavHostController,
    currentScreen: MainComposeDestination,
    drawerState: DrawerState,
    scope: CoroutineScope,
    content: @Composable (PaddingValues) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
            CustomNavigationDrawer(
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
                },
                content = content
            )
        }
    }
}
