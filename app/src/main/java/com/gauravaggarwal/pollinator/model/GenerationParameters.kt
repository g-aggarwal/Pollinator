package com.gauravaggarwal.pollinator.model

data class GenerationParameters(
    val prompt: String,
    val width: Int,
    val height: Int,
    val seed: Int? = null,
    val model: String,
    val noLogo: Boolean = DEFAULT_NO_LOGO,
    val private: Boolean = DEFAULT_PRIVATE
) {
    companion object {
        const val DEFAULT_NO_LOGO = false
        const val DEFAULT_PRIVATE = false
    }
}