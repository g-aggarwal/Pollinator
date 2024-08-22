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
package com.gauravaggarwal.pollinator.ui.screens

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gauravaggarwal.pollinator.R
import com.gauravaggarwal.pollinator.data.GenerationRepository
import com.gauravaggarwal.pollinator.model.GenerationParameters
import com.gauravaggarwal.pollinator.model.GenerationResult
import com.gauravaggarwal.pollinator.model.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class PollinatorViewModel(
    private val repository: GenerationRepository
) :  ViewModel() {
    private val _uiState = MutableStateFlow(PollinatorUiState())
    val uiState: StateFlow<PollinatorUiState> = _uiState

    fun onPromptChanged(newPrompt: String) {
        _uiState.update { currentState ->
            currentState.copy(
                prompt = newPrompt
            )
        }
    }

    // TODO: Future Implementation
    fun clearPrompt() {
        _uiState.update { currentState ->
            currentState.copy(
                prompt = "",
                bitmap = null,
                isLoading = false,
                isError = false,
                errorMessage = null,
            )
        }
    }

    fun closeDisplay() {
        _uiState.update { currentState ->
            currentState.copy(
                bitmap = null,
                isLoading = false,
                isError = false,
                errorMessage = null,
            )
        }
    }

    fun saveImage(context: Context) {
        if (_uiState.value.bitmap != null) {
            saveBitmap(
                context,
                _uiState.value.bitmap!!,
                Bitmap.CompressFormat.PNG,
                "image/png",
                "pollinator_${System.currentTimeMillis()}.png"
            )
            // TODO : add save verification
            val text = context.getString(R.string.image_saved)
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration) // in Activity
            toast.show()
        }
        closeDisplay()
    }

    fun generateImage() {
        _uiState.update { currentState ->
            currentState.copy(
                bitmap = null,
                isLoading = true,
                isError = false,
            )
        }
        viewModelScope.launch {
            when (val result = repository.generate(getParameters())) {
                is GenerationResult.Success -> {
                    // Handle successful response
                    handleSuccess(result.bitmap)
                }
                is GenerationResult.Error -> {
                    // Handle generic error
                    handleError("Error: ${result.exception.message}")
                }
                is GenerationResult.RequestError -> {
                    // Handle request error
                    handleError("Request error occurred Code:" + " ${result.code} : ${result.message}")
                }
                is GenerationResult.InvalidResponseError -> {
                    // Handle invalid response
                    handleError("Invalid response from server")
                }
                is GenerationResult.ServerError -> {
                    // Handle empty response
                    handleError("Server Error")
                }
                is GenerationResult.NetworkError -> {
                    // Handle network error
                    handleError("Network Error")
                }
            }
        }
    }

    private fun getParameters(): GenerationParameters {
        return GenerationParameters(
            prompt = _uiState.value.prompt,
            width = _uiState.value.width,
            height = _uiState.value.height,
            model = _uiState.value.model.value,
            noLogo = _uiState.value.noLogo,
            private = _uiState.value.noFeed,
            seed = _uiState.value.seed,
        )
    }

    private fun handleSuccess(bitmap: Bitmap) {
        _uiState.update { currentState ->
            currentState.copy(
                bitmap = bitmap,
                isLoading = false,
                isError = false,
                errorMessage = null,
            )
        }
    }

    private fun handleError(errorMessage: String) {
        _uiState.update { currentState ->
            currentState.copy(
                bitmap = null,
                isLoading = false,
                isError = true,
                errorMessage = errorMessage
            )
        }
    }

    @Throws(IOException::class)
    fun saveBitmap(
        context: Context,
        bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        mimeType: String,
        displayName: String
    ): Uri {

        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
            put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
        }

        val resolver = context.contentResolver
        var uri: Uri? = null

        try {
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                ?: throw IOException("Failed to create new MediaStore record.")

            resolver.openOutputStream(uri)?.use {
                if (!bitmap.compress(format, 95, it))
                    throw IOException("Failed to save bitmap.")
            } ?: throw IOException("Failed to open output stream.")

            return uri

        } catch (e: IOException) {
            uri?.let { orphanUri ->
                // Don't leave an orphan entry in the MediaStore
                resolver.delete(orphanUri, null, null)
            }
            throw e
        }
    }

    fun onModelChanged(model: Model) {
        _uiState.update { currentState ->
            currentState.copy(
                model = model
            )
        }
    }

    fun onWidthChanged(width: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                width = width,
            )
        }
    }

    fun onHeightChanged(height: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                height = height,
            )
        }
    }



    fun onSeedChanged(seed: Int?) {
        _uiState.update { currentState ->
            currentState.copy(
                seed = seed
            )
        }
    }

    fun onNoLogoChanged(noLogo: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                noLogo = noLogo
            )
        }
    }

    fun onNoFeedChanged(noFeed: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                noFeed = noFeed
            )
        }
    }

    fun resetError() {
        _uiState.update { currentState ->
            currentState.copy(
                isError = false,
                errorMessage = null,
            )
        }
    }
}