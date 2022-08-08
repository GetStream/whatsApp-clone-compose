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

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.getstream.whatsappclone.calls.info.WhatsAppCallHistoryInfo
import io.getstream.whatsappclone.chats.messages.WhatsAppMessages
import io.getstream.whatsappclone.model.WhatsAppUser
import io.getstream.whatsappclone.ui.WhatsAppTabPager
import io.getstream.whatsappclone.ui.WhatsAppTopBar

fun NavGraphBuilder.whatsAppHomeNavigation(
  composeNavigator: AppComposeNavigator
) {
  composable(route = WhatsAppScreens.Home.name) {
    Scaffold(topBar = { WhatsAppTopBar() }) { padding ->
      WhatsAppTabPager(
        modifier = Modifier.padding(padding),
        composeNavigator = composeNavigator
      )
    }
  }

  composable(
    route = WhatsAppScreens.Messages.name,
    arguments = WhatsAppScreens.Messages.navArguments
  ) {
    val channelId = it.arguments?.getString("channelId") ?: return@composable
    WhatsAppMessages(
      channelId = channelId,
      composeNavigator = composeNavigator,
      whatsAppMessagesViewModel = hiltViewModel()
    )
  }

  composable(
    route = WhatsAppScreens.CallInfo.name,
    arguments = WhatsAppScreens.CallInfo.navArguments
  ) {
    val whatsAppUser = it.arguments?.getParcelable<WhatsAppUser>(WhatsAppScreens.CallInfo.KEY_USER)
      ?: return@composable

    WhatsAppCallHistoryInfo(
      whatsAppUser = whatsAppUser,
      composeNavigator = composeNavigator
    )
  }
}
