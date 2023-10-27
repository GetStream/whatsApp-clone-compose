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

package io.getstream.whatsappclone.video.initializer

import android.content.Context
import android.util.Base64
import androidx.startup.Initializer
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.whatsappclone.video.BuildConfig
import java.nio.charset.StandardCharsets

class StreamVideoInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    val userId = "stream"
    // initialize Stream Video SDK
    StreamVideoBuilder(
      context = context,
      apiKey = BuildConfig.STREAM_API_KEY,
      token = devToken(userId),
      user = User(
        id = userId,
        name = "stream",
        image = "http://placekitten.com/200/300",
        role = "admin"
      )
    ).build()
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}

fun devToken(userId: String): String {
  require(userId.isNotEmpty()) { "User id must not be empty" }
  val header = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" //  {"alg": "HS256", "typ": "JWT"}
  val devSignature = "devtoken"
  val payload: String =
    Base64.encodeToString(
      "{\"user_id\":\"$userId\"}".toByteArray(StandardCharsets.UTF_8),
      Base64.NO_WRAP
    )
  return "$header.$payload.$devSignature"
}
