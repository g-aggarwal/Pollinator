package com.gauravaggarwal.pollinator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.gauravaggarwal.pollinator.data.PollinationsAiRepository
import com.gauravaggarwal.pollinator.ui.PollinatorApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PollinatorTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                ) {
                    PollinatorApp(repository = PollinationsAiRepository())
                }
            }
        }
    }
}

@Composable
fun PollinatorTheme(content: @Composable () -> Unit) {
    val customColors = darkColorScheme(
        primary = colorResource(id = R.color.lime_green),
        onPrimary = Color.Black,
    )

    CompositionLocalProvider {
        MaterialTheme(
            colorScheme = customColors,
            content = content
        )
    }
}
