package com.gauravaggarwal.pollinator.data

import android.graphics.Bitmap

interface ImageGeneratorRepository {
    suspend fun generate(prompt: String): Bitmap?
}