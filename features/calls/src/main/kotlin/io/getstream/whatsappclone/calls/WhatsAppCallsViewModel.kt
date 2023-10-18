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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.whatsappclone.data.coroutines.WhileSubscribedOrRetained
import io.getstream.whatsappclone.data.repository.CallHistoryRepository
import io.getstream.whatsappclone.model.WhatsAppUser
import io.getstream.whatsappclone.navigation.AppComposeNavigator
import io.getstream.whatsappclone.navigation.WhatsAppScreens
import io.getstream.whatsappclone.uistate.WhatsAppUserExtensive
import io.getstream.whatsappclone.uistate.WhatsAppUserUiState
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class WhatsAppCallsViewModel @Inject constructor(
  callHistoryRepository: CallHistoryRepository,
  private val composeNavigator: AppComposeNavigator
) : ViewModel() {

  val whatsAppUserState: StateFlow<WhatsAppUserUiState> =
    callHistoryRepository.getCallHistoryUsersStream()
      .flatMapLatest {
        if (it.isSuccess) {
          flowOf(
            WhatsAppUserUiState.Success(
              WhatsAppUserExtensive(it.getOrThrow())
            )
          )
        } else {
          flowOf(WhatsAppUserUiState.Error)
        }
      }
      .stateIn(
        scope = viewModelScope,
        started = WhileSubscribedOrRetained,
        initialValue = WhatsAppUserUiState.Loading
      )

  fun navigateToCallInfo(whatsAppUser: WhatsAppUser) {
    composeNavigator.navigate(WhatsAppScreens.CallInfo.createRoute(whatsAppUser = whatsAppUser))
  }
}
