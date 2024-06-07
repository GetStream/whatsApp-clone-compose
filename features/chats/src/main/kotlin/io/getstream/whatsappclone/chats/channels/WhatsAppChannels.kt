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

package io.getstream.whatsappclone.chats.channels

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.whatsappclone.chats.theme.WhatsAppChatTheme
import io.getstream.whatsappclone.designsystem.icon.WhatsAppIcons
import io.getstream.whatsappclone.designsystem.theme.GREEN500

@Composable
fun WhatsAppChannels(
  whatsChannelsViewModel: WhatsChannelsViewModel = hiltViewModel()
) {
  WhatsAppChatTheme {
    Box(modifier = Modifier.fillMaxSize()) {
      ChannelsScreen(
        isShowingHeader = false,
        onChannelClick = { channel ->
          whatsChannelsViewModel.navigateToMessages(channel.cid)
        }
      )

      FloatingActionButton(
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .padding(16.dp)
          .size(58.dp),
        containerColor = GREEN500,
        shape = CircleShape,
        onClick = { whatsChannelsViewModel.createChannel() }
      ) {
        Icon(
          imageVector = WhatsAppIcons.Message,
          contentDescription = null,
          tint = Color.White
        )
      }
    }
  }
}
