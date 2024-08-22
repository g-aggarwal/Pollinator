package com.gauravaggarwal.pollinator.model

import android.graphics.Bitmap

sealed class GenerationResult {
    data class Success(val bitmap: Bitmap) : GenerationResult()
    data class Error(val exception: Throwable) : GenerationResult()
    data class RequestError(val message: String, val code: Int) : GenerationResult()
    data object InvalidResponseError : GenerationResult()
    data object NetworkError : GenerationResult()
    data object ServerError : GenerationResult()
}