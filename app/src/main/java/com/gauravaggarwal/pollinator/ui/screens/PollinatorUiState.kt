package com.gauravaggarwal.pollinator.ui.screens

import android.graphics.Bitmap
import com.gauravaggarwal.pollinator.model.Size
import com.gauravaggarwal.pollinator.model.Sizes
import com.gauravaggarwal.pollinator.model.Model
import com.gauravaggarwal.pollinator.model.Models

data class PollinatorUiState(
    val prompt: String = "",
    val bitmap: Bitmap? = null,
    val areAdvancedOptionsVisible: Boolean = false,
    val seed: Int? = null,
    val model: Model = Models.getDefault(),
    val size: Size = Sizes.getDefault(),
    val noLogo: Boolean = false,
    val noFeed: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
)