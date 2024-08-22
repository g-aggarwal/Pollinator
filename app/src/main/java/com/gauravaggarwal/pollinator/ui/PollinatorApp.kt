package com.gauravaggarwal.pollinator.ui

import androidx.compose.runtime.Composable
import com.gauravaggarwal.pollinator.data.GenerationRepository
import com.gauravaggarwal.pollinator.ui.screens.PollinatorScreen
import com.gauravaggarwal.pollinator.ui.screens.PollinatorViewModel

@Composable
fun PollinatorApp(
    repository: GenerationRepository
) {
    PollinatorScreen(pollinatorViewModel = PollinatorViewModel(repository))
}
