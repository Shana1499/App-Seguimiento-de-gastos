package com.example.appseguimientogastos.ui.compose.mainscreen

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.appseguimientogastos.R
import com.example.compose.AppSeguimientoGastosTheme

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

@Composable
fun TopEndNavigationButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                Icons.Outlined.ArrowForward,
                contentDescription = "Localized description"
            )
        }
    }
}

@Composable
fun OverviewTitleComposable(modifier: Modifier = Modifier, title: String) {
    Row(modifier=modifier) {
        Text(text = title, style = MaterialTheme.typography.displayLarge)
        TopEndNavigationButton(onClick = { /*TODO*/ })
    }
}

@Composable
fun DividerComposable(modifier: Modifier = Modifier) {
    Row(
        modifier.padding(
            top = dimensionResource(id = R.dimen.default_normalpadding),
        )
    ) {
        Divider()
    }
}

@Composable
fun ContentSummaryComposable(modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Row() {
        Text(
            modifier = modifier.padding(top = dimensionResource(id = R.dimen.default_normalpadding)),
            text = stringResource(R.string.resumen),
            style = MaterialTheme.typography.displaySmall

        )
        Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            ExtraInfoItemButton(
                modifier = modifier,
                expanded = expanded,
                onClick = { expanded = !expanded })
        }
    }

    // if expanded=true show 5 first element of a list,
    if (expanded) {
        Text(text = "First Element")
        Text(text = "Second...")
    }
    // else only show the first one
    else {
        Text(text = "First Element")

    }
}


/**
 * Plantilla de Card
 * */
@Composable
fun OverviewCard(modifier: Modifier = Modifier, title: String) {

    ElevatedCard(
        modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.default_normalpadding))

    ) {
        Column(
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                // Title
                OverviewTitleComposable(title = title)

                //Current Money Value
                Text(text = "0.00 €", style = MaterialTheme.typography.displayMedium)

                DividerComposable()

                ContentSummaryComposable()

            }
        }

    }
}

@Composable
private fun ExtraInfoItemButton(
    expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = ""
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OverviewComposablePreview() {
    AppSeguimientoGastosTheme {
        Surface(
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {

                IncomeCard()
                ExpensesCard()
                SavingsCard()

            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OverviewComposableDarkPreview() {
    AppSeguimientoGastosTheme {
        Surface(
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.default_normalpadding))

            ) {
                IncomeCard()
                ExpensesCard()
                SavingsCard()
            }
        }
    }
}
