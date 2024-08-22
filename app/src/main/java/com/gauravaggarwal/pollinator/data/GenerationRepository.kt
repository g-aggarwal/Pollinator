package com.gauravaggarwal.pollinator.data

import com.gauravaggarwal.pollinator.model.GenerationResult
import com.gauravaggarwal.pollinator.model.GenerationParameters

interface GenerationRepository {
    suspend fun generate(generationParameters: GenerationParameters): GenerationResult
}