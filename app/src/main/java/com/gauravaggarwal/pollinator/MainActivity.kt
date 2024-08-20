package com.gauravaggarwal.pollinator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.gauravaggarwal.pollinator.ui.PollinatorApp
import com.gauravaggarwal.pollinator.ui.theme.PollinatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PollinatorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PollinatorApp()
                }
            }
        }
    }
}
