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

package io.getstream.whatsappclone.chats.reactions

import io.getstream.chat.android.compose.ui.util.DefaultReactionResolver
import io.getstream.chat.android.compose.ui.util.ReactionResolver

/**
 * The reactions this app supports, as emoji. Stream Chat Compose 7 renders reactions as emoji
 * through a [ReactionResolver] instead of drawables.
 */
private val whatsAppCloneReactions: Map<String, String?> = linkedMapOf(
  "good" to "\uD83D\uDC4D", // thumbs up
  "love" to "\u2764\uFE0F", // red heart
  "smile" to "\uD83D\uDE04", // smiling face with open mouth and smiling eyes
  "joy" to "\uD83D\uDE02", // face with tears of joy
  "wink" to "\uD83D\uDE09" // winking face
)

fun whatsAppCloneReactionResolver(): ReactionResolver = DefaultReactionResolver(
  emojiMapping = whatsAppCloneReactions,
  defaultReactions = whatsAppCloneReactions.keys.toList()
)
