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
package com.gauravaggarwal.pollinator.model

import android.graphics.Bitmap

sealed class GenerationResult {
    data class Success(val bitmap: Bitmap) : GenerationResult()
    data class Error(val exception: Throwable) : GenerationResult()
    data class RequestError(val message: String, val code: Int) : GenerationResult()
    data object InvalidResponseError : GenerationResult()
    data object NetworkError : GenerationResult()
    data object ServerError : GenerationResult()
}