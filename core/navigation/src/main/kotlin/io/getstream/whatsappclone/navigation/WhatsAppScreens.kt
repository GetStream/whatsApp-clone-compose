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

package io.getstream.whatsappclone.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import io.getstream.whatsappclone.model.WhatsAppUser
import io.getstream.whatsappclone.navigation.navtypes.WhatsAppUserType

sealed class WhatsAppScreens(
  val route: String,
  val navArguments: List<NamedNavArgument> = emptyList()
) {
  val name: String = route.appendArguments(navArguments)

  // home screen
  data object Home : WhatsAppScreens("home")

  // message screen
  data object Messages : WhatsAppScreens(
    route = "messages",
    navArguments = listOf(navArgument("channelId") { type = NavType.StringType })
  ) {
    fun createRoute(channelId: String) =
      name.replace("{${navArguments.first().name}}", channelId)
  }

  // call info screen
  data object CallInfo : WhatsAppScreens(
    route = "call_info",
    navArguments = listOf(
      navArgument("user") {
        type = WhatsAppUserType()
        nullable = false
      }
    )
  ) {
    const val KEY_USER = "user"
    fun createRoute(whatsAppUser: WhatsAppUser) =
      name.replace("{${navArguments.first().name}}", WhatsAppUserType.encodeToString(whatsAppUser))
  }

  // video call screen
  data object VideoCall : WhatsAppScreens(
    route = "video_call",
    navArguments = listOf(
      navArgument("call_id") {
        type = NavType.StringType
        nullable = false
      },
      navArgument("video_call") {
        type = NavType.BoolType
        nullable = false
      }
    )
  ) {
    const val KEY_CALL_ID = "call_id"
    const val KEY_VIDEO_ID = "video_call"

    fun createRoute(callId: String, videoCall: Boolean) =
      name.replace("{${navArguments[0].name}}", callId)
        .replace("{${navArguments[1].name}}", videoCall.toString())
  }
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
  val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
    .orEmpty()
  val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
    .orEmpty()
  return "$this$mandatoryArguments$optionalArguments"
}
