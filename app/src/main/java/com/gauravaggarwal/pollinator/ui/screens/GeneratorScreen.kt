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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gauravaggarwal.pollinator.R
import com.gauravaggarwal.pollinator.data.PollinationsAiRepository

private const val CHARACTER_LIMIT = 400

@Composable
fun PollinatorScreen(
    pollinatorViewModel: PollinatorViewModel,
) {
    val imageGeneratorUiState by pollinatorViewModel.uiState.collectAsState()
    var showAdvancedOptions by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bee_white_no_bg),
                contentDescription = stringResource(R.string.app_logo),
                modifier = Modifier
                    .padding(16.dp)
                    .size(64.dp)
            )
            OutlinedTextField(
                value = imageGeneratorUiState.prompt,
                onValueChange = { newValue ->
                    if (newValue.length <= CHARACTER_LIMIT) {
                        pollinatorViewModel.onPromptChanged(newValue)
                    }
                },
                label = { Text(stringResource(R.string.label_prompt)) },
                minLines = 3,
                maxLines = 5,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Dismiss the keyboard
                        focusManager.clearFocus()
                    }
                )
            )

            Text(
                text = stringResource(R.string.label_advanced_options),
                color = Color.White,
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        showAdvancedOptions = !showAdvancedOptions; focusManager.clearFocus()
                    }
            )

            if (showAdvancedOptions) {
                AdvancedOptions(
                    pollinatorViewModel = pollinatorViewModel,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(
                onClick = {
                    focusManager.clearFocus()
                    pollinatorViewModel.generateImage()
                },
                shape = RoundedCornerShape(4.dp),
                enabled = imageGeneratorUiState.prompt
                    .trim()
                    .isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(stringResource(R.string.label_pollinate))
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                imageGeneratorUiState.isLoading -> {
                    // Show a floating loading indicator
                    Dialog(onDismissRequest = { /* No-op */ }) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                imageGeneratorUiState.isError -> {
                    // Show an error dialog
                    imageGeneratorUiState.errorMessage?.let {
                        AlertDialog(
                            title = { Text(text = "Error") },
                            text = { Text(text = it) },
                            onDismissRequest = { /* Handle dismiss */ },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        // Handle button click, e.g., reset error state
                                        pollinatorViewModel.resetError()
                                    }
                                ) {
                                    Text("X")
                                }
                            }
                        )
                    }
                }
                imageGeneratorUiState.bitmap != null -> {
                    ResultScreen(pollinatorViewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun PollinatorScreenPreview() {
    PollinatorScreen(PollinatorViewModel(PollinationsAiRepository()))
}