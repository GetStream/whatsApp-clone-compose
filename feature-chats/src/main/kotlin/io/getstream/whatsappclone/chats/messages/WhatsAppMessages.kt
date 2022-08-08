/*
 * Copyright 2022 Stream.IO, Inc. All Rights Reserved.
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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.whatsappclone.chats.theme.WhatsAppChatTheme
import io.getstream.whatsappclone.navigation.AppComposeNavigator

@Composable
fun WhatsAppMessages(
  channelId: String,
  composeNavigator: AppComposeNavigator,
  whatsAppMessagesViewModel: WhatsAppMessagesViewModel
) {
  LaunchedEffect(key1 = channelId) {
    whatsAppMessagesViewModel.handleEvents(
      WhatsAppMessageEvent.FetchChannel(channelId)
    )
  }

  WhatsAppChatTheme {
    Column(Modifier.fillMaxSize()) {
      WhatsAppMessageTopBar(
        viewModel = whatsAppMessagesViewModel,
        composeNavigator = composeNavigator
      )

      MessagesScreen(
        channelId = channelId,
        showHeader = false,
        onBackPressed = { composeNavigator.navigateUp() }
      )
    }
  }
}
