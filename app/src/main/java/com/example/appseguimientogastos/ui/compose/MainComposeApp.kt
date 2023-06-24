package com.example.appseguimientogastos.ui.compose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.appseguimientogastos.R
import com.example.compose.AppSeguimientoGastosTheme


@Composable
fun MainComposeApp(
    modifier: Modifier = Modifier
) {

    AppSeguimientoGastosTheme {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Column(modifier.padding(dimensionResource(id = R.dimen.default_padding))) {

                IncomeCard()

                ExpensesCard()

                SavingsCard()

            }


        }


    }
}

/**
 * Card de la Dashboard
 * */
@Composable
fun DashBoardCard(modifier: Modifier = Modifier) {

}

/**
 * Card de Ingresos
 * */
@Composable
fun IncomeCard(modifier: Modifier = Modifier) {
    Row(modifier.padding(dimensionResource(id=R.dimen.default_padding))) {
        OverviewCard(modifier)
    }


}

/**
 * Card de Gastos
 * */
@Composable
fun ExpensesCard(modifier: Modifier = Modifier) {
    Row(modifier.padding(dimensionResource(id=R.dimen.default_padding))) {
        OverviewCard(modifier)
    }
}

/**
 * Card de Ahorro
 * */
@Composable
fun SavingsCard(modifier: Modifier = Modifier) {
    Row(modifier.padding(dimensionResource(id=R.dimen.default_padding))) {
        OverviewCard(modifier)
    }
}

/**
 * Plantilla de Card
 * */
@Composable
fun OverviewCard(modifier: Modifier = Modifier) {
    Card {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(dimensionResource(id=R.dimen.default_padding))
        ) {
            Text(text = "Title", style = MaterialTheme.typography.displayMedium)
            Text(text = "0.00 €", style = MaterialTheme.typography.displaySmall)


        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainComposeAppPreview() {
    MainComposeApp()

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainComposeAppDarkPreview() {
    MainComposeApp()

}