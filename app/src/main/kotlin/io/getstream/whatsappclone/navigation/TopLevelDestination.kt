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

package io.getstream.whatsappclone.navigation

import io.getstream.whatsappclone.R.string.tab_call
import io.getstream.whatsappclone.R.string.tab_chat
import io.getstream.whatsappclone.R.string.tab_status
import io.getstream.whatsappclone.designsystem.icon.Icon
import io.getstream.whatsappclone.designsystem.icon.WhatsAppIcons

data class TopLevelDestination(
  val route: String,
  val selectedIcon: Icon? = null,
  val unselectedIcon: Icon? = null,
  val iconTextId: Int? = null
)

val TOP_LEVEL_DESTINATIONS = listOf(

  TopLevelDestination(
    route = WhatsAppPage.Camera.route,
    selectedIcon = Icon.ImageVectorIcon(WhatsAppIcons.Camera),
    unselectedIcon = Icon.ImageVectorIcon(WhatsAppIcons.Camera)
  ),
  TopLevelDestination(
    route = WhatsAppPage.Chats.route,
    iconTextId = tab_chat
  ),
  TopLevelDestination(
    route = WhatsAppPage.Status.route,
    iconTextId = tab_status
  ),
  TopLevelDestination(
    route = WhatsAppPage.Calls.route,
    iconTextId = tab_call
  )
)

sealed class WhatsAppPage(
  val route: String,
  val index: Int
) {
  object Camera : WhatsAppPage("camera", index = 0)
  object Chats : WhatsAppPage("chats", index = 1)
  object Status : WhatsAppPage("status", index = 2)
  object Calls : WhatsAppPage("calls", index = 3)
}
