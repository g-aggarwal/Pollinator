package com.gauravaggarwal.pollinator.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.gauravaggarwal.pollinator.ui.screens.ImageGeneratorScreen
import com.gauravaggarwal.pollinator.ui.screens.PollinatorViewModel

@Composable
fun PollinatorApp() {
    Surface(
    ) {
        ImageGeneratorScreen(
            pollinatorViewModel = PollinatorViewModel(),
        )
    }
}