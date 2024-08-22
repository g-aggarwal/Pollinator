package com.gauravaggarwal.pollinator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gauravaggarwal.pollinator.R

@Composable
fun DisplayScreen(pollinatorViewModel: PollinatorViewModel) {
    val imageGeneratorUiState by pollinatorViewModel.uiState.collectAsState()
    val bitmap = imageGeneratorUiState.bitmap!!
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = stringResource(R.string.description_generated_image),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 48.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Button(
                    onClick = { pollinatorViewModel.saveImage(context) },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                ) {
                    Text(stringResource(R.string.label_save))
                }
                Button(
                    onClick = { pollinatorViewModel.closeDisplay() },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                ) {
                    Text(stringResource(R.string.label_discard))
                }
            }
        }
    }
}