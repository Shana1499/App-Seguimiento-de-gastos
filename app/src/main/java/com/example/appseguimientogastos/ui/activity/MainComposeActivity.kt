package com.example.appseguimientogastos.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appseguimientogastos.ui.compose.components.CustoNavigationDrawer
import com.example.appseguimientogastos.ui.compose.components.MainComposeAppBar
import com.example.appseguimientogastos.ui.data.getCurrentMonth
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.local.Type
import com.example.appseguimientogastos.ui.data.monthList
import com.example.appseguimientogastos.ui.navigation.Main
import com.example.appseguimientogastos.ui.navigation.MainComposeNavHost
import com.example.appseguimientogastos.ui.navigation.navigateSingleTopTo
import com.example.appseguimientogastos.ui.navigation.tabRowScreens
import com.example.appseguimientogastos.ui.view_model.MainViewModel
import com.example.compose.AppSeguimientoGastosTheme
import org.koin.androidx.compose.getViewModel

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComposeApp()
        }

    }
}

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

        //VIEW MODEL
        val viewModel:MainViewModel = getViewModel()
        val myItems by viewModel.myItems.observeAsState(initial = emptyList())


        val listIncomes = listOf<ItemVO>(
            ItemVO(
                id = 0,
                origin = "prueba1Incomes",
                price = 12.0,
                type = Type.INCOMES.typeName,
                month = monthList[6].name
            ), ItemVO(
                id = 0,
                origin = "prueba2Incomes",
                price = 14.0,
                type = Type.INCOMES.typeName,
                month = monthList[6].name
            ), ItemVO(
                id = 0,
                origin = "prueba3Incomes",
                price = 16.0,
                type = Type.INCOMES.typeName,
                month = monthList[6].name
            ),
            ItemVO(
                id = 0,
                origin = "prueba4Incomes",
                price = 12.0,
                type = Type.INCOMES.typeName,
                month = monthList[0].name
            )
        )

        listIncomes.forEach { item-> viewModel.addEntity(item = item)}

        //UI
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

@Preview(showBackground = true)
@Composable
fun MainComposeAppPreview() {
    MainComposeApp()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainComposeAppDarkPreview() {
    MainComposeApp()
}
