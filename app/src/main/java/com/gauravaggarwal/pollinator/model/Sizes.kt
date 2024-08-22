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

private const val DEFAULT = 1024
private val sizes = listOf(
    512,
    640,
    720,
    768,
    1024,
)

class Sizes {
    companion object {
        fun getSizeList(): List<Int> {
            return sizes
        }

        fun getDefaultSize(): Int {
            return DEFAULT
        }
    }
}