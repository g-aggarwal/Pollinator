package com.gauravaggarwal.pollinator.ui.screens

import android.graphics.Bitmap

data class PollinatorUiState(
    val prompt: String = "",
    val bitmap: Bitmap? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
)