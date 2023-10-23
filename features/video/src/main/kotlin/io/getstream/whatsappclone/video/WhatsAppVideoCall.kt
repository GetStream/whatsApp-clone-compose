/*
 * Copyright 2023 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.getstream.whatsappclone.video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.core.Call
import io.getstream.whatsappclone.designsystem.component.WhatsAppLoadingIndicator
import io.getstream.whatsappclone.uistate.WhatsAppVideoUiState

@Composable
fun WhatsAppVideoCall(
  id: String,
  viewModel: WhatsAppVideoCallViewModel = hiltViewModel()
) {
  val uiState by viewModel.videoUiSate.collectAsStateWithLifecycle()

  LaunchedEffect(key1 = id) {
    viewModel.joinCall(type = "default", id = id.replace(":", ""))
  }

  when (uiState) {
    is WhatsAppVideoUiState.Success ->
      WhatsAppVideoCallContent(call = (uiState as WhatsAppVideoUiState.Success).data)

    is WhatsAppVideoUiState.Error -> WhatsAppVideoCallError()

    else -> WhatsAppVideoLoading()
  }
}

@Composable
fun WhatsAppVideoCallContent(
  call: Call
) {
  VideoTheme {
    CallContent(call = call)
  }
}

@Composable
fun WhatsAppVideoCallError() {
  Box(modifier = Modifier.fillMaxSize()) {
    Text(
      modifier = Modifier.align(Alignment.Center),
      text = "Something went wrong; failed to join a call",
      fontSize = 14.sp,
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onTertiary
    )
  }
}

@Composable
fun WhatsAppVideoLoading() {
  Box(modifier = Modifier.fillMaxSize()) {
    WhatsAppLoadingIndicator(modifier = Modifier.align(Alignment.Center))
  }
}
