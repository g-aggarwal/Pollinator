/*
 * This file is part of Pollinator.
 *
 * Pollinator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Pollinator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Pollinator. If not, see <https://www.gnu.org/licenses/>.
 */
package com.gauravaggarwal.pollinator.ui.screens

import android.graphics.Bitmap
import com.gauravaggarwal.pollinator.model.Model
import com.gauravaggarwal.pollinator.model.Models
import com.gauravaggarwal.pollinator.model.Sizes

data class PollinatorUiState(
    val prompt: String = "",
    val bitmap: Bitmap? = null,
    val seed: Int? = null,
    val model: Model = Models.getDefaultModel(),
    val width: Int = Sizes.getDefaultSize(),
    val height: Int = Sizes.getDefaultSize(),
    val noLogo: Boolean = false,
    val noFeed: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
)
