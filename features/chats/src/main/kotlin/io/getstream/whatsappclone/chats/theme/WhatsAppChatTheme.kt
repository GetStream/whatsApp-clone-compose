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

package io.getstream.whatsappclone.chats.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.ui.theme.StreamDesign
import io.getstream.whatsappclone.chats.reactions.whatsAppCloneReactionResolver
import io.getstream.whatsappclone.designsystem.theme.DARK_GREEN300
import io.getstream.whatsappclone.designsystem.theme.GREEN200
import io.getstream.whatsappclone.designsystem.theme.GREEN450
import io.getstream.whatsappclone.designsystem.theme.GREEN600

@Composable
fun WhatsAppChatTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  // Own message bubbles take their background from the brand scale's s100 step.
  val streamColors = if (darkTheme) {
    StreamDesign.Colors.defaultDark(
      brand = StreamDesign.ColorScale.defaultDark().copy(s100 = GREEN600)
    ).copy(
      backgroundCoreApp = DARK_GREEN300,
      accentPrimary = GREEN450
    )
  } else {
    StreamDesign.Colors.default(
      brand = StreamDesign.ColorScale.defaultLight().copy(s100 = GREEN200)
    ).copy(
      accentPrimary = GREEN450
    )
  }

  ChatTheme(
    isInDarkMode = darkTheme,
    colors = streamColors,
    reactionResolver = whatsAppCloneReactionResolver(),
    content = content
  )
}
