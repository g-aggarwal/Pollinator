package com.gauravaggarwal.pollinator.model

data class Parameters(
    val prompt: String,
    val width: Int = 1024,
    val height: Int = 1024,
    val seed: Int = -1,
    val model: String = Models.DEFAULT,
    val noLogo: Boolean = false,
    val negativePrompt: String = "",
)
