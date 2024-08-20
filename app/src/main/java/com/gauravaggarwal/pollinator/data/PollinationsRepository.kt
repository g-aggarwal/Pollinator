package com.gauravaggarwal.pollinator.data

import android.graphics.Bitmap

class PollinationsRepository: ImageGeneratorRepository {
    private val remotePollinationsRepository = RemotePollinationsRepository()

    override suspend fun generate(prompt: String): Bitmap? {
        return remotePollinationsRepository.generateImage(prompt)
    }
}