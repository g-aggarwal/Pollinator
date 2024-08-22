/*
 * This file is part of Pollinator.
 *
 * Pollinator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Pollinator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Pollinator. If not, see <https://www.gnu.org/licenses/>.
 */
package com.gauravaggarwal.pollinator.data

import android.graphics.BitmapFactory
import com.gauravaggarwal.pollinator.model.GenerationParameters
import com.gauravaggarwal.pollinator.model.GenerationResult
import com.gauravaggarwal.pollinator.model.Models
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private const val BASE_URL = "https://image.pollinations.ai/prompt/"

class PollinationsAiRepository : GenerationRepository {
    private val client = OkHttpClient()

    private fun buildImageUrl(generationParameters: GenerationParameters): String {
        val urlBuilder = StringBuilder(BASE_URL)
        urlBuilder.append(urlEncode(generationParameters.prompt))
        urlBuilder.append("?width=").append(generationParameters.width)
        urlBuilder.append("&height=").append(generationParameters.height)

        if (generationParameters.seed != null) {
            urlBuilder.append("&seed=").append(generationParameters.seed)
        }
        if (generationParameters.model != Models.getDefaultModel().value) {
            urlBuilder.append("&model=").append(urlEncode(generationParameters.model))
        }
        if (generationParameters.noLogo != GenerationParameters.DEFAULT_NO_LOGO) {
            urlBuilder.append("&nologo=true")
        }
        if (generationParameters.private != GenerationParameters.DEFAULT_PRIVATE) {
            urlBuilder.append("&nofeed=true")
        }
        return urlBuilder.toString()
    }

    private fun urlEncode(target: String): String {
        return URLEncoder.encode(
            target,
            StandardCharsets.UTF_8.toString()
        )
    }

    override suspend fun generate(generationParameters: GenerationParameters): GenerationResult {
        return withContext(Dispatchers.IO) {
            try {
                val url = buildImageUrl(generationParameters)
                val request = Request.Builder()
                    .url(url)
                    .build()
                client.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val inputStream = response.body?.byteStream()
                        inputStream?.let {
                            return@withContext GenerationResult.Success(BitmapFactory.decodeStream(it))
                        }
                        return@withContext GenerationResult.InvalidResponseError
                    } else {
                        if (response.code >= 500) {
                            return@withContext GenerationResult.ServerError
                        }
                        return@withContext GenerationResult.Error(IOException("Failed response with code: ${response.code}"))
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext GenerationResult.NetworkError
            }
        }
    }
}