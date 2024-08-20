package com.gauravaggarwal.pollinator.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private const val BASE_URL = "https://image.pollinations.ai/prompt/"

class RemotePollinationsRepository {
    private val client = OkHttpClient()

    suspend fun generateImage(prompt: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            val urlEncodedPrompt = URLEncoder.encode(
                prompt,
                StandardCharsets.UTF_8.toString()
            )
            // TODO - remove this
            println("URL Encoded Prompt: $urlEncodedPrompt")
            val url = BASE_URL + urlEncodedPrompt
            // TODO - remove this
            println("URL: $url")
            null

//            val request = Request.Builder()
//                .url(url)
//                .build()
//
//            try {
//                client.newCall(request).execute().use { response ->
//                    if (response.isSuccessful) {
//                        val inputStream = response.body?.byteStream()
//                        if (inputStream != null) {
//                            return@withContext BitmapFactory.decodeStream(inputStream) // Return here
//                        } else {
//                            null
//                        }
//                    } else {
//                        null
//                    }
//                }
//            } catch (e: IOException) {
//                println("Error: ${e.message}")
//                e.printStackTrace()
//                null
//            }
        }
    }
}

