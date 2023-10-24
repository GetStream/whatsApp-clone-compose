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

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.video.android.core.StreamVideo
import io.getstream.whatsappclone.navigation.AppComposeNavigator
import io.getstream.whatsappclone.uistate.WhatsAppVideoUiState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WhatsAppVideoCallViewModel @Inject constructor(
  private val composeNavigator: AppComposeNavigator
) : ViewModel() {

  private val videoMutableUiState =
    MutableStateFlow<WhatsAppVideoUiState>(WhatsAppVideoUiState.Loading)
  val videoUiSate: StateFlow<WhatsAppVideoUiState> = videoMutableUiState

  fun joinCall(type: String, id: String) {
    viewModelScope.launch {
      val streamVideo = StreamVideo.instance()
      val activeCall = streamVideo.state.activeCall.value
      val call = if (activeCall != null) {
        if (activeCall.id != id) {
          Log.w("CallActivity", "A call with id: $id existed. Leaving.")
          // If the call id is different leave the previous call
          activeCall.leave()
          // Return a new call
          streamVideo.call(type = type, id = id)
        } else {
          // Call ID is the same, use the active call
          activeCall
        }
      } else {
        // There is no active call, create new call
        streamVideo.call(type = type, id = id)
      }
      val result = call.join(create = true)
      result.onSuccess {
        videoMutableUiState.value = WhatsAppVideoUiState.Success(call)
      }.onError {
        videoMutableUiState.value = WhatsAppVideoUiState.Error
      }
    }
  }

  fun navigateUp() {
    composeNavigator.navigateUp()
  }
}
