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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.whatsappclone.navigation.AppComposeNavigator
import io.getstream.whatsappclone.navigation.WhatsAppScreens
import javax.inject.Inject
import kotlin.random.Random
import kotlinx.coroutines.launch

@HiltViewModel
class WhatsChannelsViewModel @Inject constructor(
  private val composeNavigator: AppComposeNavigator,
  private val chatClient: ChatClient
) : ViewModel() {

  private val user = chatClient.clientState.user

  fun navigateToMessages(channelId: String) {
    composeNavigator.navigate(WhatsAppScreens.Messages.createRoute(channelId))
  }

  fun createChannel() {
    viewModelScope.launch {
      val me = user.value
      if (me != null) {
        chatClient.createChannel(
          channelType = "messaging",
          channelId = "channel${Random.nextInt(10000)}",
          memberIds = listOf(me.id),
          extraData = mapOf()
        ).await()
      }
    }
  }
}
