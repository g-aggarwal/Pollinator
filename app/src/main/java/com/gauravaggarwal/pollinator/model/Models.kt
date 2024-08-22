package com.gauravaggarwal.pollinator.model

data class Model(val displayName: String, val value: String)

class Models {
    companion object {
        private val DEFAULT =  Model("Default", "default" )
        private val TURBO =  Model("Turbo", "turbo")
        private val FLUX =  Model("Flux", "flux")

        private val models = listOf(
            DEFAULT,
            TURBO,
            FLUX
        )

        fun getModelList(): List<Model> {
            return models
        }

        fun getDefaultModel(): Model {
            return DEFAULT
        }
    }
}