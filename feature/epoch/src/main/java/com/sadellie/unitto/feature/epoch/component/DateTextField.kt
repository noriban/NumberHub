/*
 * Unitto is a unit converter for Android
 * Copyright (c) 2023 Elshan Agaev
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

package com.sadellie.unitto.feature.epoch.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sadellie.unitto.core.ui.theme.NumbersTextStyleDisplayMedium
import com.sadellie.unitto.feature.epoch.R

@Composable
fun DateTextField(
    modifier: Modifier,
    date: String
) {
    val inputWithPadding: String = date.padEnd(14, '0')

    val hour = inputWithPadding.substring(0, 2)
    val minute = inputWithPadding.substring(2, 4)
    val second = inputWithPadding.substring(4, 6)
    val day = inputWithPadding.substring(6, 8)
    val month = inputWithPadding.substring(8, 10)
    val year = inputWithPadding.substring(10, 14)

    fun inFocus(range: Int): Boolean = date.length > range

    Column(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .horizontalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.End
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            DateTextAnimated(text = hour, focus = inFocus(0))
            DateText(text = ":", focus = inFocus(0))
            DateTextAnimated(text = minute, focus = inFocus(2))
            DateText(text = ":", focus = inFocus(2))
            DateTextAnimated(text = second, focus = inFocus(4))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Row {
                DateTextAnimated(text = day, focus = inFocus(6))
                DateText(text = stringResource(R.string.day_short), focus = inFocus(6))
            }
            Row {
                DateTextAnimated(text = month, focus = inFocus(8))
                DateText(text = stringResource(R.string.month_short), focus = inFocus(8))
            }
            Row {
                DateTextAnimated(text = year, focus = inFocus(10))
                DateText(text = stringResource(R.string.year_short), focus = inFocus(10))
            }
        }
    }
}

@Composable
private fun BaseDateText(
    focus: Boolean,
    content: @Composable (Color) -> Unit
) {
    val color = animateColorAsState(
        if (focus) {
            MaterialTheme.colorScheme.onBackground
        } else {
            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        }
    )
    content(color.value)
}

@Composable
private fun DateText(
    focus: Boolean = true,
    text: String
) {
    BaseDateText(focus = focus) { color ->
        Text(
            text = text,
            style = NumbersTextStyleDisplayMedium,
            color = color
        )
    }
}

@Composable
private fun DateTextAnimated(
    focus: Boolean = true,
    text: String
) {
    BaseDateText(focus = focus) { color ->
        AnimatedContent(
            targetState = text,
            transitionSpec = {
                if (targetState.toInt() > initialState.toInt()) {
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }
        ) {
            Text(
                text = it,
                style = NumbersTextStyleDisplayMedium,
                color = color
            )
        }
    }
}
