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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.utils.onError
import io.getstream.chat.android.client.utils.onSuccess
import io.getstream.whatsappclone.network.Dispatcher
import io.getstream.whatsappclone.network.WhatsAppDispatchers
import io.getstream.whatsappclone.uistate.WhatsAppMessageUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhatsAppMessagesViewModel @Inject constructor(
  @Dispatcher(WhatsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
  private val chatClient: ChatClient
) : ViewModel() {
  private val messageMutableUiState =
    MutableStateFlow<WhatsAppMessageUiState>(WhatsAppMessageUiState.Loading)
  val messageUiSate: StateFlow<WhatsAppMessageUiState> = messageMutableUiState

  fun handleEvents(whatsAppMessageEvent: WhatsAppMessageEvent) {
    when (whatsAppMessageEvent) {
      is WhatsAppMessageEvent.FetchChannel -> fetchChannel(whatsAppMessageEvent.channelId)
    }
  }

  private fun fetchChannel(channelId: String) {
    viewModelScope.launch(ioDispatcher) {
      val result = chatClient.channel(channelId).watch().await()
      result.onSuccess {
        messageMutableUiState.value = WhatsAppMessageUiState.Success(result.data())
      }.onError {
        messageMutableUiState.value = WhatsAppMessageUiState.Error
      }
    }
  }
}

sealed interface WhatsAppMessageEvent {
  class FetchChannel(val channelId: String) : WhatsAppMessageEvent
}
