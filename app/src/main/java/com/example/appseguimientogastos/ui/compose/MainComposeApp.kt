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
import androidx.compose.ui.unit.dp
import com.example.appseguimientogastos.R
import com.example.compose.AppSeguimientoGastosTheme

private val DefaultPadding = 12.dp

@Composable
fun MainComposeApp(
    modifier: Modifier = Modifier
) {

    AppSeguimientoGastosTheme {
        Surface(
            modifier = modifier.fillMaxSize()
        ) {
            Column(modifier.padding(DefaultPadding)) {
                IngresosCard()
                GastosCard()
                AhorroCard()
            }


        }


    }
}


@Composable
fun DashBoardCard(modifier: Modifier = Modifier) {

}

@Composable
fun IngresosCard(modifier: Modifier = Modifier) {

    OverviewCard(modifier)


}

@Composable
fun GastosCard(modifier: Modifier = Modifier) {
    OverviewCard(modifier)

}

@Composable
fun AhorroCard(modifier: Modifier = Modifier) {
    OverviewCard(modifier)

}

@Composable
fun OverviewCard(modifier: Modifier = Modifier) {
    Card {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(DefaultPadding)
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