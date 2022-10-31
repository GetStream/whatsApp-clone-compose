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

package io.getstream.whatsappclone.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkWhatsAppColorScheme = darkColorScheme(
  primary = DARK_GREEN200,
  primaryContainer = DARK_GREEN300,
  secondary = GREEN500,
  background = DARK_GREEN300,
  tertiary = WHITE200,
  onTertiary = GRAY200
)

private val LightWhatsAppColorScheme = lightColorScheme(
  primary = GREEN500,
  primaryContainer = GREEN700,
  secondary = GREEN300,
  background = WHITE200,
  tertiary = WHITE200,
  onTertiary = GRAY200
)

/** Light Android background theme */
private val LightAndroidBackgroundTheme = BackgroundTheme(color = Color.White)

/** Dark Android background theme */
private val DarkAndroidBackgroundTheme = BackgroundTheme(color = DARK_GREEN300)

@Composable
fun WhatsAppCloneComposeTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) DarkWhatsAppColorScheme else LightWhatsAppColorScheme
  val backgroundTheme = if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme

  CompositionLocalProvider(
    LocalBackgroundTheme provides backgroundTheme
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
  }
}
