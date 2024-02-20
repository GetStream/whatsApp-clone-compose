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
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.compose.ui.components.call.controls.ControlActions
import io.getstream.video.android.compose.ui.components.call.controls.actions.FlipCameraAction
import io.getstream.video.android.compose.ui.components.call.controls.actions.LeaveCallAction
import io.getstream.video.android.compose.ui.components.call.controls.actions.ReactionAction
import io.getstream.video.android.compose.ui.components.call.controls.actions.ToggleCameraAction
import io.getstream.video.android.compose.ui.components.call.controls.actions.ToggleMicrophoneAction
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.mapper.ReactionMapper
import io.getstream.video.android.mock.StreamPreviewDataUtils
import io.getstream.video.android.mock.previewCall
import io.getstream.whatsappclone.designsystem.component.WhatsAppLoadingIndicator
import io.getstream.whatsappclone.uistate.WhatsAppVideoUiState

@Composable
fun WhatsAppVideoCall(
  id: String,
  videoCall: Boolean,
  viewModel: WhatsAppVideoCallViewModel = hiltViewModel()
) {
  val uiState by viewModel.videoUiSate.collectAsStateWithLifecycle()

  LaunchedEffect(key1 = id) {
    viewModel.joinCall(type = "default", id = id.replace(":", ""))
  }

  when (uiState) {
    is WhatsAppVideoUiState.Success ->
      WhatsAppVideoCallContent(
        call = (uiState as WhatsAppVideoUiState.Success).data,
        videoCall = videoCall,
        onBackPressed = { viewModel.navigateUp() }
      )

    is WhatsAppVideoUiState.Error -> WhatsAppVideoCallError()

    else -> WhatsAppVideoLoading()
  }
}

@Composable
private fun WhatsAppVideoCallContent(
  call: Call,
  videoCall: Boolean,
  onBackPressed: () -> Unit
) {
  val isCameraEnabled by call.camera.isEnabled.collectAsStateWithLifecycle()
  val isMicrophoneEnabled by call.microphone.isEnabled.collectAsStateWithLifecycle()
  var isShowingReactionDialog by remember { mutableStateOf(false) }

  DisposableEffect(key1 = call.id) {
    if (!videoCall) {
      call.camera.setEnabled(false)
    }

    onDispose { call.leave() }
  }

  VideoTheme {
    Box(modifier = Modifier.fillMaxSize()) {
      CallContent(
        call = call,
        onBackPressed = onBackPressed,
        controlsContent = {
          if (videoCall) {
            ControlActions(
              call = call,
              actions = listOf(
                {
                  ReactionAction(
                    modifier = Modifier.size(52.dp),
                    onCallAction = { isShowingReactionDialog = true }
                  )
                },
                {
                  ToggleCameraAction(
                    modifier = Modifier.size(52.dp),
                    isCameraEnabled = isCameraEnabled,
                    onCallAction = { call.camera.setEnabled(it.isEnabled) }
                  )
                },
                {
                  ToggleMicrophoneAction(
                    modifier = Modifier.size(52.dp),
                    isMicrophoneEnabled = isMicrophoneEnabled,
                    onCallAction = { call.microphone.setEnabled(it.isEnabled) }
                  )
                },
                {
                  FlipCameraAction(
                    modifier = Modifier.size(52.dp),
                    onCallAction = { call.camera.flip() }
                  )
                },
                {
                  LeaveCallAction(
                    modifier = Modifier.size(52.dp),
                    onCallAction = { onBackPressed.invoke() }
                  )
                }
              )
            )
          } else {
            ControlActions(
              call = call,
              actions = listOf(
                {
                  ReactionAction(
                    modifier = Modifier.size(52.dp),
                    onCallAction = { isShowingReactionDialog = true }
                  )
                },
                {
                  ToggleMicrophoneAction(
                    modifier = Modifier.size(52.dp),
                    isMicrophoneEnabled = isMicrophoneEnabled,
                    onCallAction = { call.microphone.setEnabled(it.isEnabled) }
                  )
                },
                {
                  LeaveCallAction(
                    modifier = Modifier.size(52.dp),
                    onCallAction = { onBackPressed.invoke() }
                  )
                }
              )
            )
          }
        }
      )

      if (isShowingReactionDialog) {
        ReactionsMenu(
          call = call,
          reactionMapper = ReactionMapper.defaultReactionMapper(),
          onDismiss = { isShowingReactionDialog = false }
        )
      }
    }
  }
}

@Composable
private fun WhatsAppVideoCallError() {
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
private fun WhatsAppVideoLoading() {
  Box(modifier = Modifier.fillMaxSize()) {
    WhatsAppLoadingIndicator(modifier = Modifier.align(Alignment.Center))
  }
}

@Preview
@Composable
private fun WhatsAppVideoCallContentPreview() {
  StreamPreviewDataUtils.initializeStreamVideo(LocalContext.current)
  VideoTheme {
    WhatsAppVideoCallContent(
      call = previewCall,
      videoCall = true
    ) {}
  }
}
