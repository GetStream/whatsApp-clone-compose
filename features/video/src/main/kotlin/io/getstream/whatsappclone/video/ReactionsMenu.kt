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

@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package io.getstream.whatsappclone.video

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.mapper.ReactionMapper
import io.getstream.video.android.mock.StreamMockUtils
import io.getstream.video.android.mock.mockCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Default reaction item data
 *
 * @param displayText the text visible on the screen.
 * @param emojiCode the code of the emoji e.g. ":like:"
 * */
private data class ReactionItemData(val displayText: String, val emojiCode: String)

/**
 * Default defined reactions.
 *
 * There is one main reaction, and a list of other reactions. The main reaction is shown on top of the rest.
 */
private object DefaultReactionsMenuData {
  val mainReaction = ReactionItemData("Raise hand", ":raise-hand:")
  val defaultReactions = listOf(
    ReactionItemData("Fireworks", ":fireworks:"),
    ReactionItemData("Wave", ":hello:"),
    ReactionItemData("Like", ":raise-hand:"),
    ReactionItemData("Dislike", ":hate:"),
    ReactionItemData("Smile", ":smile:"),
    ReactionItemData("Heart", ":heart:")
  )
}

/**
 * Reactions menu. The reaction menu is a dialog displaying the list of reactions found in
 * [DefaultReactionsMenuData].
 *
 * @param call the call object.
 * @param reactionMapper the mapper of reactions to map from emoji code into UTF see: [ReactionMapper]
 * @param onDismiss on dismiss listener.
 */
@Composable
internal fun ReactionsMenu(
  call: Call,
  reactionMapper: ReactionMapper,
  onDismiss: () -> Unit
) {
  val scope = rememberCoroutineScope()
  val modifier = Modifier
    .background(
      color = Color.White,
      shape = RoundedCornerShape(2.dp)
    )
    .wrapContentWidth()
  val onEmojiSelected: (emoji: String) -> Unit = {
    sendReaction(scope, call, it, onDismiss)
  }

  Dialog(onDismiss) {
    Card(
      modifier = modifier.wrapContentWidth()
    ) {
      Column(Modifier.padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.Center) {
          ReactionItem(
            modifier = Modifier
              .background(
                color = Color(0xFFF1F4F0),
                shape = RoundedCornerShape(2.dp)
              )
              .fillMaxWidth(),
            textModifier = Modifier.fillMaxWidth(),
            reactionMapper = reactionMapper,
            reaction = DefaultReactionsMenuData.mainReaction,
            onEmojiSelected = onEmojiSelected
          )
        }
        FlowRow(
          horizontalArrangement = Arrangement.Center,
          maxItemsInEachRow = 3,
          verticalArrangement = Arrangement.Center
        ) {
          DefaultReactionsMenuData.defaultReactions.forEach {
            ReactionItem(
              modifier = modifier,
              reactionMapper = reactionMapper,
              onEmojiSelected = onEmojiSelected,
              reaction = it
            )
          }
        }
      }
    }
  }
}

@Composable
private fun ReactionItem(
  modifier: Modifier = Modifier,
  textModifier: Modifier = Modifier,
  reactionMapper: ReactionMapper,
  reaction: ReactionItemData,
  onEmojiSelected: (emoji: String) -> Unit
) {
  val mappedEmoji = reactionMapper.map(reaction.emojiCode)
  Box(
    modifier = modifier
      .clickable {
        onEmojiSelected(reaction.emojiCode)
      }
      .padding(2.dp)
  ) {
    Text(
      textAlign = TextAlign.Center,
      modifier = textModifier.padding(12.dp),
      text = "$mappedEmoji ${reaction.displayText}"
    )
  }
}

private fun sendReaction(scope: CoroutineScope, call: Call, emoji: String, onDismiss: () -> Unit) {
  scope.launch {
    call.sendReaction("default", emoji)
    onDismiss()
  }
}

@Preview
@Composable
private fun ReactionItemPreview() {
  StreamMockUtils.initializeStreamVideo(LocalContext.current)
  ReactionItem(
    reactionMapper = ReactionMapper.defaultReactionMapper(),
    onEmojiSelected = {
      // Ignore
    },
    reaction = DefaultReactionsMenuData.mainReaction
  )
}

@Preview
@Composable
private fun ReactionMenuPreview() {
  VideoTheme {
    StreamMockUtils.initializeStreamVideo(LocalContext.current)
    ReactionsMenu(
      call = mockCall,
      reactionMapper = ReactionMapper.defaultReactionMapper(),
      onDismiss = { }
    )
  }
}
