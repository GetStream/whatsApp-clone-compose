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

package io.getstream.whatsappclone.chats.messages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import io.getstream.whatsappclone.designsystem.component.WhatsAppLoadingIndicator
import io.getstream.whatsappclone.designsystem.icon.WhatsAppIcons
import io.getstream.whatsappclone.designsystem.theme.WhatsAppCloneComposeTheme
import io.getstream.whatsappclone.uistate.WhatsAppMessageUiState

@Composable
fun WhatsAppMessageTopBar(
  channelId: String,
  whatsAppMessagesViewModel: WhatsAppMessagesViewModel = hiltViewModel(),
  messageUiState: WhatsAppMessageUiState,
  onBackClick: () -> Unit
) {
  TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    navigationIcon = {
      Icon(
        modifier = Modifier
          .size(26.dp)
          .clickable {
            onBackClick()
          },
        imageVector = WhatsAppIcons.ArrowBack,
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
      )
    },
    title = {
      WhatsAppMessageUserInfo(messageUiState = messageUiState)
    },
    actions = {
      Icon(
        modifier = Modifier
          .size(26.dp)
          .clickable {
            whatsAppMessagesViewModel.navigateToVideoCall(channelId = channelId, videoCall = true)
          },
        imageVector = WhatsAppIcons.Video,
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
      )

      Spacer(modifier = Modifier.size(16.dp))

      Icon(
        modifier = Modifier
          .size(26.dp)
          .clickable {
            whatsAppMessagesViewModel.navigateToVideoCall(channelId = channelId, videoCall = false)
          },
        imageVector = WhatsAppIcons.Call,
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
      )

      Spacer(modifier = Modifier.size(16.dp))

      Icon(
        modifier = Modifier.size(26.dp),
        imageVector = WhatsAppIcons.MoreVert,
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
      )
    },
    backgroundColor = MaterialTheme.colorScheme.primary
  )
}

@Composable
private fun WhatsAppMessageUserInfo(
  messageUiState: WhatsAppMessageUiState
) {
  when (messageUiState) {
    WhatsAppMessageUiState.Loading -> WhatsAppLoadingIndicator()
    WhatsAppMessageUiState.Error -> Unit
    is WhatsAppMessageUiState.Success -> {
      Row {
        GlideImage(
          modifier = Modifier
            .size(32.dp)
            .clip(CircleShape),
          imageModel = {
            messageUiState.data.image.takeIf { it.isNotEmpty() }
              ?: io.getstream.whatsappclone.designsystem.R.drawable.stream_logo
          },
          component = rememberImageComponent {
            +CrossfadePlugin()
          },
          previewPlaceholder = io.getstream.whatsappclone.designsystem.R.drawable.placeholder
        )

        Text(
          modifier = Modifier.padding(start = 12.dp),
          text = messageUiState.data.name,
          color = MaterialTheme.colorScheme.tertiary,
          style = MaterialTheme.typography.bodyLarge
        )
      }
    }
  }
}

@Preview
@Composable
private fun WhatsAppTopBarPreview() {
  WhatsAppCloneComposeTheme {
    WhatsAppMessageTopBar(
      channelId = "",
      messageUiState = WhatsAppMessageUiState.Loading,
      onBackClick = {}
    )
  }
}

@Preview
@Composable
private fun WhatsAppTopBarDarkPreview() {
  WhatsAppCloneComposeTheme(darkTheme = true) {
    WhatsAppMessageTopBar(
      channelId = "",
      messageUiState = WhatsAppMessageUiState.Loading,
      onBackClick = {}
    )
  }
}
