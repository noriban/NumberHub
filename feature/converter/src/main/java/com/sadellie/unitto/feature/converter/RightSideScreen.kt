/*
 * Unitto is a calculator for Android
 * Copyright (c) 2023-2024 Elshan Agaev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sadellie.unitto.feature.converter

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sadellie.unitto.core.base.OutputFormat
import com.sadellie.unitto.core.base.R
import com.sadellie.unitto.core.ui.common.EmptyScreen
import com.sadellie.unitto.core.ui.common.SearchBar
import com.sadellie.unitto.core.ui.common.textfield.FormatterSymbols
import com.sadellie.unitto.core.ui.common.textfield.formatExpression
import com.sadellie.unitto.data.common.format
import com.sadellie.unitto.data.converter.UnitID
import com.sadellie.unitto.data.model.UnitGroup
import com.sadellie.unitto.data.model.UnitsListSorting
import com.sadellie.unitto.data.model.unit.AbstractUnit
import com.sadellie.unitto.data.model.unit.DefaultUnit
import com.sadellie.unitto.data.model.unit.NormalUnit
import com.sadellie.unitto.data.model.unit.NumberBaseUnit
import com.sadellie.unitto.feature.converter.components.FavoritesButton
import com.sadellie.unitto.feature.converter.components.UnitsList
import java.math.BigDecimal

@Composable
internal fun RightSideRoute(
    viewModel: ConverterViewModel,
    navigateUp: () -> Unit,
    navigateToUnitGroups: () -> Unit,
) {
    when (
        val uiState = viewModel.rightSideUIState.collectAsStateWithLifecycle().value
    ) {
        is RightSideUIState.Loading -> EmptyScreen()
        is RightSideUIState.Ready ->
            RightSideScreen(
                uiState = uiState,
                onQueryChange = viewModel::queryChangeRight,
                toggleFavoritesOnly = viewModel::favoritesOnlyChange,
                updateUnitTo = viewModel::updateUnitTo,
                favoriteUnit = viewModel::favoriteUnit,
                navigateUp = navigateUp,
                navigateToUnitGroups = navigateToUnitGroups,
            )
    }
}

@Composable
private fun RightSideScreen(
    uiState: RightSideUIState.Ready,
    onQueryChange: (TextFieldValue) -> Unit,
    toggleFavoritesOnly: (Boolean) -> Unit,
    updateUnitTo: (AbstractUnit) -> Unit,
    favoriteUnit: (AbstractUnit) -> Unit,
    navigateUp: () -> Unit,
    navigateToUnitGroups: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SearchBar(
                query = uiState.query,
                onQueryChange = onQueryChange,
                navigateUp = navigateUp,
                trailingIcon = {
                    FavoritesButton(uiState.favorites) {
                        toggleFavoritesOnly(!uiState.favorites)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        val resources = LocalContext.current.resources
        UnitsList(
            modifier = Modifier.padding(paddingValues),
            groupedUnits = uiState.units,
            navigateToUnitGroups = navigateToUnitGroups,
            currentUnitId = uiState.unitTo.id,
            supportLabel = {
                formatUnitToSupportLabel(
                    unitFrom = uiState.unitFrom,
                    unitTo = it,
                    input = uiState.input,
                    shortName = resources.getString(it.shortName),
                    scale = uiState.scale,
                    outputFormat = uiState.outputFormat,
                    formatterSymbols = uiState.formatterSymbols,
                    readyCurrencies = uiState.currencyRateUpdateState is CurrencyRateUpdateState.Ready,
                )
            },
            onClick = {
                onQueryChange(TextFieldValue())
                updateUnitTo(it)
                navigateUp()
            },
            favoriteUnit = { favoriteUnit(it) }
        )
    }
}

private fun formatUnitToSupportLabel(
    unitFrom: AbstractUnit?,
    unitTo: AbstractUnit?,
    input: String,
    shortName: String,
    scale: Int,
    outputFormat: Int,
    formatterSymbols: FormatterSymbols,
    readyCurrencies: Boolean,
): String {
    if ((unitFrom?.group == UnitGroup.CURRENCY) and !readyCurrencies) return shortName
    if (input.isEmpty()) return shortName

    try {
        if ((unitFrom is DefaultUnit) and (unitTo is DefaultUnit)) {
            unitFrom as DefaultUnit
            unitTo as DefaultUnit

            val conversion = unitFrom
                .convert(unitTo, BigDecimal(input))
                .format(scale, outputFormat)
                .formatExpression(formatterSymbols)

            return "$conversion $shortName"
        }

        if ((unitFrom is NumberBaseUnit) and (unitTo is NumberBaseUnit)) {
            unitFrom as NumberBaseUnit
            unitTo as NumberBaseUnit

            val conversion = unitFrom.convert(unitTo, input).uppercase()

            return "$conversion $shortName"
        }
    } catch (e: Exception) {
        return shortName
    }

    return shortName
}

@Preview
@Composable
private fun RightSideScreenPreview() {
    val units: Map<UnitGroup, List<AbstractUnit>> = mapOf(
        UnitGroup.LENGTH to listOf(
            NormalUnit(UnitID.meter, BigDecimal.valueOf(1.0E+18), UnitGroup.LENGTH, R.string.unit_meter, R.string.unit_meter_short),
            NormalUnit(UnitID.kilometer, BigDecimal.valueOf(1.0E+21), UnitGroup.LENGTH, R.string.unit_kilometer, R.string.unit_kilometer_short),
            NormalUnit(UnitID.nautical_mile, BigDecimal.valueOf(1.852E+21), UnitGroup.LENGTH, R.string.unit_nautical_mile, R.string.unit_nautical_mile_short),
            NormalUnit(UnitID.inch, BigDecimal.valueOf(25_400_000_000_000_000), UnitGroup.LENGTH, R.string.unit_inch, R.string.unit_inch_short),
            NormalUnit(UnitID.foot, BigDecimal.valueOf(304_800_000_000_002_200), UnitGroup.LENGTH, R.string.unit_foot, R.string.unit_foot_short),
            NormalUnit(UnitID.yard, BigDecimal.valueOf(914_400_000_000_006_400), UnitGroup.LENGTH, R.string.unit_yard, R.string.unit_yard_short),
            NormalUnit(UnitID.mile, BigDecimal.valueOf(1_609_344_000_000_010_500_000.0), UnitGroup.LENGTH, R.string.unit_mile, R.string.unit_mile_short),
        )
    )

    RightSideScreen(
        uiState = RightSideUIState.Ready(
            unitFrom = units.values.first().first(),
            units = units,
            query = TextFieldValue(),
            favorites = false,
            sorting = UnitsListSorting.USAGE,
            unitTo = units.values.first()[1],
            input = "100",
            scale = 3,
            outputFormat = OutputFormat.PLAIN,
            formatterSymbols = FormatterSymbols.Spaces,
            currencyRateUpdateState = CurrencyRateUpdateState.Nothing
        ),
        onQueryChange = {},
        toggleFavoritesOnly = {},
        updateUnitTo = {},
        favoriteUnit = {},
        navigateUp = {},
        navigateToUnitGroups = {}
    )
}
