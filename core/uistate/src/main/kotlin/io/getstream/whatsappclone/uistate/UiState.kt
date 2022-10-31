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

package io.getstream.whatsappclone.uistate

import androidx.compose.runtime.Immutable
import com.skydoves.sealedx.core.Extensive
import com.skydoves.sealedx.core.annotations.ExtensiveModel
import com.skydoves.sealedx.core.annotations.ExtensiveSealed
import io.getstream.chat.android.client.models.Channel

/**
 * Generates restartable and skippable UI states based on KSP and extensive models.
 * @see (SealedX)[https://github.com/skydoves/sealedx]
 */
@ExtensiveSealed(
  models = [
    ExtensiveModel(type = Channel::class, name = "WhatsAppMessage"),
    ExtensiveModel(type = WhatsAppUserExtensive::class, name = "WhatsAppUser")
  ]
)
@Immutable
sealed interface UiState {
  data class Success(val data: Extensive) : UiState
  object Loading : UiState
  object Error : UiState
}
