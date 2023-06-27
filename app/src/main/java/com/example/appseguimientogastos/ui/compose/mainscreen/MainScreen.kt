package com.example.appseguimientogastos.ui.compose.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.ui.compose.MainComposeApp
import com.example.compose.md_theme_light_Ahorro
import com.example.compose.md_theme_light_gastos
import com.example.compose.md_theme_light_ingreso

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    LazyColumn {
        item {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                DashBoardCard()
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardCard(modifier: Modifier = Modifier) {
    val progressList: List<Float> = listOf(0.2f, 0.4f, 0.4f)
    val colorList = listOf(
        md_theme_light_ingreso,
        md_theme_light_gastos,
        md_theme_light_Ahorro
    )
    val titleList = listOf(R.string.ingresos, R.string.gastos, R.string.ahorro)

    // Get the screen dimensions
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Calculate a scaling factor based on the screen dimensions
    val scalingFactor = screenWidth / 400f

    // Use the scaling factor to scale the content
    val scaledPadding =
        dimensionResource(id = R.dimen.default_normalpadding).value * scalingFactor.value

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(id = R.dimen.default_normalpadding),
            )
    ) {
        // Title
        Row(
            modifier = modifier.padding(
                dimensionResource(id = R.dimen.default_normalpadding),
            )
        ) {
            Text(
                text = stringResource(id = R.string.dashboard),
                style = MaterialTheme.typography.displayLarge

            )
        }

        //Content
        Row(modifier = modifier.fillMaxWidth()) {
            //CustomContentBar
            Column(
                modifier = modifier.padding(
                    start = dimensionResource(id = R.dimen.default_normalpadding),
                    end = dimensionResource(id = R.dimen.default_normalpadding)
                )
            ) {
                Box(contentAlignment = Alignment.TopStart) {
                    CustomProgressBar(progressList = progressList, colorList, screenWidth / 3)

                }


            }

            Column(modifier = modifier.padding((dimensionResource(id = R.dimen.default_normalpadding)))) {
                Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Column() {
                        //Presupuesto+infoProgressBar
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
                            colorList = colorList,
                            canvasSize = screenWidth / 2
                        )
                    }
                }
            }
        }

        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.default_normalpadding))
        ) {
            FilterChip(
                modifier = modifier.padding(end = dimensionResource(id = R.dimen.default_normalpadding)),
                label = {
                    Text(text = "Mes Anterior")
                },
                selected = true,
                onClick = { /*TODO*/ }
            )
            FilterChip(
                modifier = modifier.padding(end = dimensionResource(id = R.dimen.default_normalpadding)),
                label = {
                    Text(text = "Mes Actual")
                },
                selected = true,
                onClick = { /*TODO*/ }
            )
            FilterChip(
                label = {
                    Text(text = "Selcciona")
                },
                selected = true,
                onClick = { /*TODO*/ },
                leadingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )
        }

    }
}


@Composable
fun CustomProgressBar(progressList: List<Float>, colorList: List<Color>, canvasSize: Dp) {
    val colorScheme = MaterialTheme.colorScheme

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))
    ) {
        Canvas(
            modifier = Modifier.size(canvasSize)
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

        Text(
            text = "Mes",
            style = MaterialTheme.typography.displayMedium
        )
    }
}


@Composable
fun InfoProgressbar(
    modifier: Modifier = Modifier,
    progressList: List<Float>,
    titleList: List<Int>,
    colorList: List<Color>,
    canvasSize: Dp
) {
    val drawSize = Modifier.size(canvasSize / 10, 7.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        progressList.forEachIndexed { index, _ ->
            Row(modifier.padding(bottom = dimensionResource(id = R.dimen.default_smallpadding))) {
                Canvas(
                    modifier = drawSize
                ) {

                    drawRoundRect(
                        color = colorList[index],
                        topLeft = Offset(0f, 23f),
                        size = size,
                        cornerRadius = CornerRadius(20f)
                    )


                }
                Text(
                    modifier = modifier.padding(
                        start = dimensionResource(id = R.dimen.default_normalpadding),
                        end = dimensionResource(id = R.dimen.default_normalpadding)
                    ),
                    text = "xx% " + stringResource(id = titleList[index]),
                    style = MaterialTheme.typography.displaySmall

                )

            }


        }
    }

}


/**
 * Card de Ingresos
 * */
@Composable
fun IncomeCard(modifier: Modifier = Modifier) {
    OverviewCard(modifier, stringResource(R.string.ingresos))
}

/**
 * Card de Gastos
 * */
@Composable
fun ExpensesCard(modifier: Modifier = Modifier) {

    OverviewCard(modifier, stringResource(R.string.gastos))

}

/**
 * Card de Ahorro
 * */
@Composable
fun SavingsCard(modifier: Modifier = Modifier) {

    OverviewCard(modifier, stringResource(R.string.ahorro))

}

/**
 * Plantilla de Card
 * */
@Composable
fun OverviewCard(modifier: Modifier = Modifier, title: String) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(id = R.dimen.default_normalpadding),
            )
    ) {
        Column(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.default_normalpadding))
        ) {
            Row() {
                Text(text = title, style = MaterialTheme.typography.displayLarge)
                Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    IconButton(
                        onClick = { /* doSomething() */ }
                    ) {
                        Icon(
                            Icons.Outlined.ArrowForward,
                            contentDescription = "Localized description"
                        )
                    }
                }
            }


            Text(text = "0.00 €", style = MaterialTheme.typography.displayMedium)
            Text(
                text = stringResource(R.string.resumen) + ":",
                style = MaterialTheme.typography.displaySmall
            )
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