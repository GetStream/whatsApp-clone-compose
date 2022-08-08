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

package io.getstream.whatsappclone.calls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.getstream.whatsappclone.designsystem.icon.WhatsAppIcons
import io.getstream.whatsappclone.designsystem.theme.GREEN450
import io.getstream.whatsappclone.designsystem.theme.getTitleColor
import io.getstream.whatsappclone.model.WhatsAppUser

@Composable
fun WhatsAppCallHistory(
  whatsAppUser: WhatsAppUser,
  onItemClicked: () -> Unit
) {
  Box {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .clickable { onItemClicked() }
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      GlideImage(
        modifier = Modifier
          .size(56.dp)
          .clip(CircleShape),
        imageModel = whatsAppUser.picture,
        previewPlaceholder = io.getstream.whatsappclone.designsystem.R.drawable.placeholder
      )

      Column(modifier = Modifier.padding(start = 12.dp)) {
        Text(
          text = whatsAppUser.name,
          style = MaterialTheme.typography.titleMedium,
          color = getTitleColor()
        )

        Spacer(modifier = Modifier.size(6.dp))

        Text(
          text = whatsAppUser.login,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onTertiary
        )
      }
    }

    Icon(
      modifier = Modifier
        .align(Alignment.CenterEnd)
        .padding(12.dp),
      imageVector = WhatsAppIcons.Call,
      tint = GREEN450,
      contentDescription = null
    )
  }
}
