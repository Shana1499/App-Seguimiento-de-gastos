package com.example.appseguimientogastos.ui.compose.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.appseguimientogastos.R
import com.example.appseguimientogastos.data.Month
import com.example.appseguimientogastos.data.getCurrentMonth
import com.example.appseguimientogastos.data.getPreviouMonth
import com.example.appseguimientogastos.data.monthList
import com.example.compose.AppSeguimientoGastosTheme
import com.example.compose.md_theme_light_Ahorro
import com.example.compose.md_theme_light_gastos
import com.example.compose.md_theme_light_ingreso

/**
 * DashBoard Card
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardCard(modifier: Modifier = Modifier, currentMonth: MutableState<Month>) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(id = R.dimen.default_normalpadding),
            ),
        elevation = CardDefaults.elevatedCardElevation()

    ) {
        // Title
        TitleDashBoardComposable()

        //Content
        ContentDashBoardComposable(currentMonth = currentMonth)
    }
}


@Composable
fun TitleDashBoardComposable(modifier: Modifier = Modifier) {
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
}

@Composable
fun ColumnProgressCircleComposable(
    progressList: List<Float>,
    colorList: List<Color>,
    canvasSizeCircle: Dp,
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
) {
    Column(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.default_normalpadding),
            end = dimensionResource(id = R.dimen.default_normalpadding)
        )
    ) {
        Box(contentAlignment = Alignment.TopStart) {
            CustomProgressBar(
                progressList = progressList,
                colorList = colorList,
                canvasSizeCircle = canvasSizeCircle,
                currentMonth = currentMonth
            )

        }


    }
}


@Composable
fun ColumnInfoProgressCircleComposable(
    modifier: Modifier = Modifier,
    progressList: List<Float>,
    titleList: List<Int>,
    colorList: List<Color>,
    canvasSizeInfo: Dp
) {
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
                    canvasSize = canvasSizeInfo
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipsComposables(
    modifier: Modifier = Modifier,
    currentMonth: MutableState<Month>,
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedMonthText by remember { mutableStateOf(monthList[0].name) }

    FilterChip(
        modifier = modifier.padding(end = dimensionResource(id = R.dimen.default_smallpadding)),
        label = {
            Text(text = "Mes Anterior")
        },
        selected = true,
        onClick = {
            currentMonth.value = getPreviouMonth()
        },
        elevation = FilterChipDefaults.elevatedFilterChipElevation()
    )

    FilterChip(
        modifier = modifier.padding(end = dimensionResource(id = R.dimen.default_smallpadding)),
        label = {
            Text(text ="Mes Actual")
        },
        selected = true,
        onClick = {
            currentMonth.value = getCurrentMonth()
        },
        elevation = FilterChipDefaults.elevatedFilterChipElevation()
    )


    Column(
    ) {

        FilterChip(
            label = {
                Text(text = selectedMonthText)
            },
            selected = true,
            onClick = { showDialog = !showDialog },
            trailingIcon = {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = "Localized description",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            },
            elevation = FilterChipDefaults.elevatedFilterChipElevation(),
        )

        DropdownMenu(expanded = showDialog, onDismissRequest = { showDialog = false }) {


            monthList.forEach { monthSelected ->

                DropdownMenuItem(
                    text = { Text(monthSelected.name) },
                    onClick = {
                        selectedMonthText = monthSelected.name
                        currentMonth.value = monthSelected
                        showDialog = false
                    })
                Divider()


            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentDashBoardComposable(modifier: Modifier = Modifier, currentMonth: MutableState<Month>) {
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

    //canvas Size
    val canvasSizeCircle = screenWidth / 3
    val canvasSizeInfo = screenWidth / 2


    Row(modifier = modifier.fillMaxWidth()) {
        //CustomProgressCircle
        ColumnProgressCircleComposable(
            progressList = progressList,
            colorList = colorList,
            canvasSizeCircle = canvasSizeCircle,
            currentMonth = currentMonth
        )

        ColumnInfoProgressCircleComposable(
            progressList = progressList,
            titleList = titleList,
            colorList = colorList,
            canvasSizeInfo = canvasSizeInfo
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.default_normalpadding)),
        horizontalArrangement = Arrangement.Center
    ) {
        FilterChipsComposables(modifier, currentMonth = currentMonth)
    }


}


@Composable
fun CustomProgressBar(
    progressList: List<Float>,
    colorList: List<Color>,
    canvasSizeCircle: Dp,
    currentMonth: MutableState<Month>
) {

    var currentTextStyle =
        when (currentMonth.value) {
            monthList[8] -> MaterialTheme.typography.headlineMedium
            monthList[9] -> MaterialTheme.typography.headlineMedium
            monthList[10] -> MaterialTheme.typography.headlineMedium
            monthList[11] -> MaterialTheme.typography.headlineMedium
            else -> MaterialTheme.typography.displayMedium

        }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))
    ) {
        Canvas(
            modifier = Modifier.size(canvasSizeCircle)
        ) {
            val strokeWidth = 30f
            var startAngle = -90f

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
            text = currentMonth.value.name,
            style = currentTextStyle
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MonthListPickerComposablePreview() {
    AppSeguimientoGastosTheme {
        Surface(
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                val currentMonth = remember { mutableStateOf(getCurrentMonth()) }

                Row() {
                    FilterChipsComposables(currentMonth = currentMonth)

                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DashBoardComposablePreview() {
    AppSeguimientoGastosTheme {
        Surface(
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                val currentMonth = remember { mutableStateOf(getCurrentMonth()) }

                DashBoardCard(Modifier, currentMonth)

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DashBoardComposableDarkPreview() {
    AppSeguimientoGastosTheme {
        Surface(
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                val currentMonth = remember { mutableStateOf(getCurrentMonth()) }

                DashBoardCard(Modifier, currentMonth)
            }
        }
    }
}