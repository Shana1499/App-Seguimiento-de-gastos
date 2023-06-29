package com.example.appseguimientogastos.ui.compose.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.Month
import com.example.appseguimientogastos.ui.compose.MainComposeApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, currentMonth: MutableState<Month>) {

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                DashBoardCard(currentMonth = currentMonth)
                IncomeCard()
                ExpensesCard()
                SavingsCard()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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