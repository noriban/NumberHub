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

package app.myzel394.numberhub.data.converter.collections

import app.myzel394.numberhub.core.base.R
import app.myzel394.numberhub.data.converter.UnitID
import app.myzel394.numberhub.data.model.converter.UnitGroup
import app.myzel394.numberhub.data.model.converter.unit.BasicUnit
import app.myzel394.numberhub.data.model.converter.unit.NormalUnit
import java.math.BigDecimal

internal val pressureCollection: List<BasicUnit> by lazy {
    listOf(
        NormalUnit(UnitID.attopascal,                            BigDecimal("1"),                      UnitGroup.PRESSURE, R.string.unit_attopascal,                           R.string.unit_attopascal_short),
        NormalUnit(UnitID.femtopascal,                           BigDecimal("1000"),                   UnitGroup.PRESSURE, R.string.unit_femtopascal,                          R.string.unit_femtopascal_short),
        NormalUnit(UnitID.picopascal,                            BigDecimal("1000000"),                   UnitGroup.PRESSURE, R.string.unit_picopascal,                           R.string.unit_picopascal_short),
        NormalUnit(UnitID.nanopascal,                            BigDecimal("1000000000"),                   UnitGroup.PRESSURE, R.string.unit_nanopascal,                           R.string.unit_nanopascal_short),
        NormalUnit(UnitID.micropascal,                           BigDecimal("1000000000000"),                  UnitGroup.PRESSURE, R.string.unit_micropascal,                          R.string.unit_micropascal_short),
        NormalUnit(UnitID.millipascal,                           BigDecimal("1000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_millipascal,                          R.string.unit_millipascal_short),
        NormalUnit(UnitID.centipascal,                           BigDecimal("10000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_centipascal,                          R.string.unit_centipascal_short),
        NormalUnit(UnitID.decipascal,                            BigDecimal("100000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_decipascal,                           R.string.unit_decipascal_short),
        NormalUnit(UnitID.pascal,                                BigDecimal("1000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_pascal,                               R.string.unit_pascal_short),
        NormalUnit(UnitID.dekapascal,                            BigDecimal("10000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_dekapascal,                           R.string.unit_dekapascal_short),
        NormalUnit(UnitID.hectopascal,                           BigDecimal("100000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_hectopascal,                          R.string.unit_hectopascal_short),
        NormalUnit(UnitID.millibar,                              BigDecimal("100000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_millibar,                             R.string.unit_millibar_short),
        NormalUnit(UnitID.bar,                                   BigDecimal("100000000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_bar,                                  R.string.unit_bar_short),
        NormalUnit(UnitID.kilopascal,                            BigDecimal("1000000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_kilopascal,                           R.string.unit_kilopascal_short),
        NormalUnit(UnitID.megapascal,                            BigDecimal("1000000000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_megapascal,                           R.string.unit_megapascal_short),
        NormalUnit(UnitID.gigapascal,                            BigDecimal("1000000000000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_gigapascal,                           R.string.unit_gigapascal_short),
        NormalUnit(UnitID.terapascal,                            BigDecimal("1000000000000000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_terapascal,                           R.string.unit_terapascal_short),
        NormalUnit(UnitID.petapascal,                            BigDecimal("1000000000000000000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_petapascal,                           R.string.unit_petapascal_short),
        NormalUnit(UnitID.exapascal,                             BigDecimal("1000000000000000000000000000000000000"),                  UnitGroup.PRESSURE, R.string.unit_exapascal,                            R.string.unit_exapascal_short),
        NormalUnit(UnitID.psi,                                   BigDecimal("6894757293178300000000"),    UnitGroup.PRESSURE, R.string.unit_psi,                                  R.string.unit_psi_short),
        NormalUnit(UnitID.ksi,                                   BigDecimal("6894757293178300000000000"),    UnitGroup.PRESSURE, R.string.unit_ksi,                                  R.string.unit_ksi_short),
        NormalUnit(UnitID.standard_atmosphere,                   BigDecimal("101325000000000000000000"),            UnitGroup.PRESSURE, R.string.unit_standard_atmosphere,                  R.string.unit_standard_atmosphere_short),
        NormalUnit(UnitID.torr,                                  BigDecimal("133322368421082810000"), UnitGroup.PRESSURE, R.string.unit_torr,                                 R.string.unit_torr_short),
        NormalUnit(UnitID.micron_of_mercury,                     BigDecimal("133322368421082810"), UnitGroup.PRESSURE, R.string.unit_micron_of_mercury,                    R.string.unit_micron_of_mercury_short),
        NormalUnit(UnitID.millimeter_of_mercury,                 BigDecimal("133322368421082810000"), UnitGroup.PRESSURE, R.string.unit_millimeter_of_mercury,                R.string.unit_millimeter_of_mercury_short),
        NormalUnit(UnitID.kilogram_force_per_square_meter,       BigDecimal("9806650000000000000"),            UnitGroup.PRESSURE, R.string.unit_kilogram_force_per_square_meter,      R.string.unit_kilogram_force_per_square_meter_short),
        NormalUnit(UnitID.kilogram_force_per_square_centimeter,  BigDecimal("98066500000000000000000"),            UnitGroup.PRESSURE, R.string.unit_kilogram_force_per_square_centimeter, R.string.unit_kilogram_force_per_square_centimeter_short),
        NormalUnit(UnitID.gram_force_per_square_centimeter,      BigDecimal("98066500000000000000"),            UnitGroup.PRESSURE, R.string.unit_gram_force_per_square_centimeter,     R.string.unit_gram_force_per_square_centimeter_short),
        NormalUnit(UnitID.pound_force_per_square_foot,           BigDecimal("47880258980000000000"),        UnitGroup.PRESSURE, R.string.unit_pound_force_per_square_foot,          R.string.unit_pound_force_per_square_foot_short),
        NormalUnit(UnitID.pound_force_per_square_inch,           BigDecimal("6894757293178300000000"),    UnitGroup.PRESSURE, R.string.unit_pound_force_per_square_inch,          R.string.unit_pound_force_per_square_inch_short),
    )
}
