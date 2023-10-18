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

package io.getstream.whatsappclone.calls

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.getstream.whatsappclone.designsystem.component.WhatsAppError
import io.getstream.whatsappclone.designsystem.component.WhatsAppLoadingColumn
import io.getstream.whatsappclone.model.WhatsAppUser
import io.getstream.whatsappclone.uistate.WhatsAppUserUiState

@Composable
fun WhatsAppCalls(
  whatsAppCallsViewModel: WhatsAppCallsViewModel = hiltViewModel()
) {
  val whatsAppUsersUiState by whatsAppCallsViewModel.whatsAppUserState.collectAsStateWithLifecycle()

  WhatsAppCallsScreen(
    whatsAppUsersUiState = whatsAppUsersUiState,
    onHistoryItemClick = whatsAppCallsViewModel::navigateToCallInfo
  )
}

@Composable
private fun WhatsAppCallsScreen(
  whatsAppUsersUiState: WhatsAppUserUiState,
  onHistoryItemClick: (WhatsAppUser) -> Unit
) {
  when (whatsAppUsersUiState) {
    WhatsAppUserUiState.Loading -> WhatsAppLoadingColumn()
    WhatsAppUserUiState.Error -> WhatsAppError()
    is WhatsAppUserUiState.Success -> {
      LazyColumn {
        items(
          items = whatsAppUsersUiState.data.whatsappUserList,
          key = { it.name }
        ) {
          WhatsAppCallHistory(whatsAppUser = it) {
            onHistoryItemClick(it)
          }
        }
      }
    }
  }
}
