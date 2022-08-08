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

package io.getstream.whatsappclone.calls.info

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.glide.GlideImage
import io.getstream.whatsappclone.designsystem.R
import io.getstream.whatsappclone.designsystem.icon.WhatsAppIcons
import io.getstream.whatsappclone.designsystem.theme.GRAY200
import io.getstream.whatsappclone.designsystem.theme.GREEN450
import io.getstream.whatsappclone.designsystem.theme.getTitleColor
import io.getstream.whatsappclone.model.WhatsAppUser
import java.util.Date

@Composable
fun WhatsAppCallHistoryInfoBody(
  modifier: Modifier,
  whatsAppUser: WhatsAppUser
) {
  ConstraintLayout(modifier = modifier.padding(12.dp)) {
    val (image, name, call, divider, location, date) = createRefs()

    GlideImage(
      modifier = Modifier
        .size(56.dp)
        .clip(CircleShape)
        .constrainAs(image) {
          start.linkTo(parent.start)
          top.linkTo(parent.top)
        },
      imageModel = whatsAppUser.picture,
      previewPlaceholder = R.drawable.placeholder
    )

    Text(
      modifier = Modifier
        .padding(horizontal = 8.dp)
        .constrainAs(name) {
          start.linkTo(image.end)
          top.linkTo(image.top)
          bottom.linkTo(image.bottom)
        },
      text = whatsAppUser.name,
      style = MaterialTheme.typography.titleMedium,
      color = getTitleColor()
    )

    Icon(
      modifier = Modifier
        .padding(12.dp)
        .constrainAs(call) {
          top.linkTo(parent.top)
          end.linkTo(parent.end)
        },
      imageVector = WhatsAppIcons.Call,
      tint = GREEN450,
      contentDescription = null
    )

    Divider(
      modifier = Modifier
        .constrainAs(divider) {
          start.linkTo(image.end)
          end.linkTo(parent.end)
          top.linkTo(image.bottom)
        }
        .padding(16.dp),
      color = GRAY200
    )

    Text(
      modifier = Modifier
        .padding(horizontal = 16.dp)
        .constrainAs(location) {
          start.linkTo(divider.start)
          top.linkTo(divider.bottom)
        },
      text = whatsAppUser.location,
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.tertiary
    )

    Text(
      modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 4.dp)
        .constrainAs(date) {
          top.linkTo(location.bottom)
          start.linkTo(location.start)
        },
      text = Date(whatsAppUser.registrationDate).toString(),
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.tertiary
    )
  }
}
