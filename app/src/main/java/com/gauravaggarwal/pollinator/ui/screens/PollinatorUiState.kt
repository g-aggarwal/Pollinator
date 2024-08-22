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
