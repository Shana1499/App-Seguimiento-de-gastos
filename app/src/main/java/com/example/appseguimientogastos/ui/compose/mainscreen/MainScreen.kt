package com.example.appseguimientogastos.ui.compose.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.compose.MainAppBar
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.compose.AppSeguimientoGastosTheme
import com.example.compose.md_theme_light_Ahorro
import com.example.compose.md_theme_light_gastos
import com.example.compose.md_theme_light_ingreso

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(
            top = dimensionResource(id = R.dimen.default_normalpadding),
            start = dimensionResource(id = R.dimen.default_normalpadding),
            end = dimensionResource(id = R.dimen.default_normalpadding)
        )
    ) {

        DashBoardCard()

        IncomeCard()

        ExpensesCard()

        SavingsCard()

    }
}

/**
 * Card de la Dashboard
 * */
@Composable
fun DashBoardCard(modifier: Modifier = Modifier) {

    val progressList: List<Float> = listOf(0.2f, 0.4f, 0.4f)
    val colorList = listOf(
        md_theme_light_ingreso,
        md_theme_light_gastos,
        md_theme_light_Ahorro
    )
    val titleList = listOf(R.string.ingresos, R.string.gastos, R.string.ahorro)

    Card(
        modifier = modifier.padding(
            bottom = dimensionResource(id = R.dimen.default_normalpadding),
        )
    ) {

        Text(
            modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding)),
            text = stringResource(id = R.string.dashboard),
            style = MaterialTheme.typography.displayLarge

        )

        Row(modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))) {

            Column(modifier.padding(end = dimensionResource(id = R.dimen.default_bigpadding))) {

                CustomProgressBar(progressList = progressList, colorList)

            }

            Column() {
                Text(
                    text = "Presupuesto: ",
                    style = MaterialTheme.typography.displaySmall

                )
                Text(
                    text = "0.00€",
                    style = MaterialTheme.typography.displayMedium

                )

                InfoProgressbar(
                    progressList = progressList,
                    titleList = titleList,
                    colorList = colorList
                )


            }


        }


    }

}


@Composable
fun CustomProgressBar(progressList: List<Float>, colorList: List<Color>) {
    val colorScheme = MaterialTheme.colorScheme

    Canvas(
        modifier = Modifier
            .size(170.dp)
            .padding(dimensionResource(id = R.dimen.default_normalpadding))
    ) {
        val strokeWidth = 30f
        var startAngle = -90f
        drawArc(
            color = colorScheme.onSurface.copy(alpha = 0.12f),
            startAngle = startAngle,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )
        progressList.forEachIndexed { index, progress ->
            val sweepAngle = progress * 360f
            drawArc(
                color = colorList[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )
            startAngle += sweepAngle
        }
    }

}

@Composable
fun InfoProgressbar(
    modifier: Modifier = Modifier,
    progressList: List<Float>,
    titleList: List<Int>,
    colorList: List<Color>
) {
    progressList.forEachIndexed { index, _ ->
        Row(modifier.padding(bottom = dimensionResource(id = R.dimen.default_smallpadding))) {
            Canvas(
                modifier = Modifier
                    .size(40.dp, 7.dp)
            ) {

                drawRoundRect(
                    color = colorList[index],
                    topLeft = Offset(0f, 23f),
                    size = size,
                    cornerRadius = CornerRadius(20f)
                )


            }
            Text(
                modifier = modifier.padding(start = 5.dp),
                text = "xx% ",
                style = MaterialTheme.typography.displaySmall

            )
            Text(
                text = stringResource(id = titleList[index]),
                style = MaterialTheme.typography.displaySmall

            )
        }


    }
}


/**
 * Card de Ingresos
 * */
@Composable
fun IncomeCard(modifier: Modifier = Modifier) {
    OverviewCard(modifier, stringResource(R.string.ingresos) + ":")


}

/**
 * Card de Gastos
 * */
@Composable
fun ExpensesCard(modifier: Modifier = Modifier) {

    OverviewCard(modifier, stringResource(R.string.gastos) + ":")

}

/**
 * Card de Ahorro
 * */
@Composable
fun SavingsCard(modifier: Modifier = Modifier) {

    OverviewCard(modifier, stringResource(R.string.ahorro) + ":")

}

/**
 * Plantilla de Card
 * */
@Composable
fun OverviewCard(modifier: Modifier = Modifier, title: String) {
    Card(
        modifier = modifier.padding(
            bottom = dimensionResource(id = R.dimen.default_normalpadding),
        )
    ) {
        Column(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.default_normalpadding))
        ) {
            Text(text = title, style = MaterialTheme.typography.displayLarge)
            Text(text = "0.00 €", style = MaterialTheme.typography.displayMedium)
            Text(
                text = stringResource(R.string.resumen) + ":",
                style = MaterialTheme.typography.displaySmall
            )
        }
        Column(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.default_normalpadding))
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Boton")
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