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

package io.getstream.whatsappclone.status

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.getstream.whatsappclone.designsystem.icon.WhatsAppIcons
import io.getstream.whatsappclone.designsystem.theme.GREEN500
import io.getstream.whatsappclone.designsystem.theme.WhatsAppCloneComposeTheme
import io.getstream.whatsappclone.designsystem.theme.getTitleColor

@Composable
fun WhatsAppStatus() {
  Box {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .clickable { }
        .padding(12.dp)
    ) {
      Row(
        modifier = Modifier
          .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        GlideImage(
          modifier = Modifier
            .size(56.dp)
            .clip(CircleShape),
          imageModel = "https://placekitten.com/200/300",
          previewPlaceholder = io.getstream.whatsappclone.designsystem.R.drawable.placeholder
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
          Text(
            text = stringResource(id = R.string.status_mine),
            style = MaterialTheme.typography.titleMedium,
            color = getTitleColor()
          )

          Spacer(modifier = Modifier.size(6.dp))

          Text(
            text = stringResource(id = R.string.status_desc),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiary
          )
        }
      }

      Spacer(modifier = Modifier.size(16.dp))

      Text(
        text = stringResource(id = R.string.recent_updates),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onTertiary
      )
    }

    FloatingActionButton(
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .padding(16.dp)
        .size(58.dp),
      backgroundColor = GREEN500,
      shape = CircleShape,
      onClick = { }
    ) {
      Icon(
        imageVector = WhatsAppIcons.Camera,
        contentDescription = null,
        tint = Color.White
      )
    }
  }
}

@Preview
@Composable
private fun WhatsAppStatusPreview() {
  WhatsAppCloneComposeTheme {
    WhatsAppStatus()
  }
}
