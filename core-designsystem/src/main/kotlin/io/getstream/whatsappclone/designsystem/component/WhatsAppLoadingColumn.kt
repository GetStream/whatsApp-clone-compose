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

package io.getstream.whatsappclone.designsystem.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.Shimmer
import io.getstream.whatsappclone.designsystem.theme.shimmerHighLight

@Composable
fun WhatsAppLoadingColumn(
  itemSize: Int = 10
) {
  LazyColumn {
    items(itemSize) {
      WhatsAppShimmer()
    }
  }
}

@Composable
private fun WhatsAppShimmer() {
  Shimmer(
    modifier = Modifier
      .fillMaxSize()
      .height(75.dp)
      .padding(vertical = 6.dp, horizontal = 12.dp)
      .clip(RoundedCornerShape(8.dp)),
    baseColor = MaterialTheme.colorScheme.background,
    highlightColor = shimmerHighLight,
    durationMillis = 350,
    dropOff = 0.65f,
    tilt = 20f
  )
}
