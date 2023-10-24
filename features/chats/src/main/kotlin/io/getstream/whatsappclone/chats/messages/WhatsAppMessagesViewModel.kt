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

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.whatsappclone.navigation.AppComposeNavigator
import io.getstream.whatsappclone.navigation.WhatsAppScreens
import io.getstream.whatsappclone.uistate.WhatsAppMessageUiState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WhatsAppMessagesViewModel @Inject constructor(
  private val chatClient: ChatClient,
  private val composeNavigator: AppComposeNavigator,
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val messageMutableUiState =
    MutableStateFlow<WhatsAppMessageUiState>(WhatsAppMessageUiState.Loading)
  val messageUiSate: StateFlow<WhatsAppMessageUiState> = messageMutableUiState

  private val channelId = savedStateHandle.get<String>("channelId")

  init {
    if (channelId != null) {
      fetchChannel(channelId = channelId)
    }
  }

  fun handleEvents(whatsAppMessageEvent: WhatsAppMessageEvent) {
    when (whatsAppMessageEvent) {
      is WhatsAppMessageEvent.NavigateUp -> composeNavigator.navigateUp()
    }
  }

  fun navigateToVideoCall(channelId: String, videoCall: Boolean) {
    composeNavigator.navigate(
      WhatsAppScreens.VideoCall.createRoute(
        callId = channelId,
        videoCall = videoCall
      )
    )
  }

  private fun fetchChannel(channelId: String) {
    viewModelScope.launch {
      val result = chatClient.channel(channelId).watch().await()
      result.onSuccess {
        messageMutableUiState.value = WhatsAppMessageUiState.Success(result.getOrThrow())
      }.onError {
        messageMutableUiState.value = WhatsAppMessageUiState.Error
      }
    }
  }
}

sealed interface WhatsAppMessageEvent {
  data object NavigateUp : WhatsAppMessageEvent
}
