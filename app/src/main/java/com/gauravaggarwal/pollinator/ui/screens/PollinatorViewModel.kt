package com.gauravaggarwal.pollinator.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gauravaggarwal.pollinator.data.PollinationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PollinatorViewModel :  ViewModel() {
    private val _uiState = MutableStateFlow(PollinatorUiState())
    val uiState: StateFlow<PollinatorUiState> = _uiState

    fun onPromptChanged(newPrompt: String) {
        _uiState.value = _uiState.value.copy(prompt = newPrompt)
    }

    fun generateImage() {
        val pollinatorRepository = PollinationsRepository()
        viewModelScope.launch {
            val bitmap = pollinatorRepository.generate(_uiState.value.prompt)
            _uiState.update { it.copy(bitmap = bitmap) }
        }
    }

    fun clearPrompt() {
        _uiState.value = _uiState.value.copy(prompt = "")
    }
}