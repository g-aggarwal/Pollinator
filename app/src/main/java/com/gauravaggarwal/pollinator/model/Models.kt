package com.gauravaggarwal.pollinator.model

class Models {
    companion object {
        const val DEFAULT = "turbo"
        private const val TURBO = "turbo"
        private const val DREAMSHAPER = "dreamshaper"
        private const val DELIBERATE = "deliberate"
        private const val PIXART = "pixart"
        private const val PLAYGROUND = "playground"
        private const val DPO = "dpo"
        private const val DALLE3XL = "dalle3xl"
        private const val FORMULAXL = "formulaxl"

        private val displayStrings = mapOf(
            TURBO to "Turbo Model",
            DREAMSHAPER to "Dreamshaper Model",
            DELIBERATE to "Deliberate Model",
            PIXART to "Pixart Model",
            PLAYGROUND to "Playground Model",
            DPO to "DPO Model",
            DALLE3XL to "DALL·E 3XL Model",
            FORMULAXL to "Formula XL Model"
        )

        fun getModels(): List<String> {
            return displayStrings.keys.toList()
        }

        fun getDisplayString(model: String): String? {
            return displayStrings[model]
        }
    }
}