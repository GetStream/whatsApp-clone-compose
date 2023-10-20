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

package io.getstream.whatsappclone.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.getstream.whatsappclone.R
import io.getstream.whatsappclone.designsystem.icon.WhatsAppIcons
import io.getstream.whatsappclone.designsystem.theme.WhatsAppCloneComposeTheme

@Composable
fun WhatsAppTopBar() {
  TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    title = {
      Text(
        text = stringResource(id = R.string.app_name),
        color = MaterialTheme.colorScheme.tertiary,
        style = MaterialTheme.typography.titleLarge
      )
    },
    actions = {
      Icon(
        modifier = Modifier.size(26.dp),
        imageVector = WhatsAppIcons.Search,
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
      )

      Spacer(modifier = Modifier.size(16.dp))

      Icon(
        modifier = Modifier.size(26.dp),
        imageVector = WhatsAppIcons.MoreVert,
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null
      )
    },
    backgroundColor = MaterialTheme.colorScheme.primary
  )
}

@Preview
@Composable
private fun WhatsAppTopBarPreview() {
  WhatsAppCloneComposeTheme {
    WhatsAppTopBar()
  }
}

@Preview
@Composable
private fun WhatsAppTopBarDarkPreview() {
  WhatsAppCloneComposeTheme(darkTheme = true) {
    WhatsAppTopBar()
  }
}
