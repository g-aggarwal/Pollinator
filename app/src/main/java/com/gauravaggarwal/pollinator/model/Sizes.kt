package com.gauravaggarwal.pollinator.model

data class Size(val width: Int, val height: Int, val ratio: String)

class Sizes {
    companion object {
        private val sizes = listOf(
            Size(1024, 1024, "1:1"),
            Size(1080, 720, "3:2"),
            Size(720, 1080, "2:3"),
            Size(768, 1024, "3:4"),
            Size(1024, 768, "4:3"),
            Size(720, 1280, "9:16"),
            Size(1920, 1080, "16:9")
        )

        fun getSizeList(): List<Size> {
            return sizes
        }

        fun getDisplayString(size: Size): String {
            return size.width.toString() + " x " + size.height.toString() + " (" + size.ratio + ")"
        }

        fun getDefault(): Size {
            return sizes[0]
        }
    }
}