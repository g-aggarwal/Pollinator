package com.gauravaggarwal.pollinator.model

private const val DEFAULT = 1024
private val sizes = listOf(
    512,
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