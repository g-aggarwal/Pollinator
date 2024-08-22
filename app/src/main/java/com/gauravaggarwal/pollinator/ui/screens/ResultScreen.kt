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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gauravaggarwal.pollinator.R

@Composable
fun ResultScreen(pollinatorViewModel: PollinatorViewModel) {
    val imageGeneratorUiState by pollinatorViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val bitmap = imageGeneratorUiState.bitmap
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bee_white_no_bg),
                contentDescription = stringResource(R.string.app_logo),
                modifier = Modifier
                    .padding(16.dp)
                    .size(64.dp)
            )

            if (bitmap != null) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = stringResource(R.string.description_generated_image),
                    contentScale = ContentScale.Crop,
                )
            } else {
                Text(
                    text = stringResource(R.string.no_image_found),
                    color = Color.White,
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.button_download), // Replace with your save icon
                    contentDescription = stringResource(R.string.label_save),
                    modifier = Modifier
                        .size(36.dp)
                        .weight(1f)
                        .clickable { pollinatorViewModel.saveImage(context) } // Add the click action
                )

                Image(
                    painter = painterResource(R.drawable.button_close), // Replace with your discard icon
                    contentDescription = stringResource(R.string.label_discard),
                    modifier = Modifier
                        .size(36.dp)
                        .weight(1f)
                        .clickable { pollinatorViewModel.closeDisplay() } // Add the click action
                )
            }
        }
    }
}