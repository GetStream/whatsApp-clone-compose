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

package io.getstream.whatsappclone.video

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.GEO
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User

@Composable
fun WhatsAppVideoCall(id: String) {
  val context = LocalContext.current

  var call: Call? by remember { mutableStateOf(null) }

  LaunchedEffect(key1 = Unit) {
    val userToken = ""
    val userId = "Ben_Skywalker"
    val callId = "dE8AsD5Qxqrt"

    // step1 - create a user.
    val user = User(
      id = userId, // any string
      name = "Tutorial", // name and image are used in the UI
      role = "admin"
    )

    // step2 - initialize StreamVideo. For a production app we recommend adding the client to your Application class or di module.
    val client = StreamVideoBuilder(
      context = context,
      apiKey = BuildConfig.STREAM_API_KEY,
      geo = GEO.GlobalEdgeNetwork,
      user = user,
      token = userToken,
      ensureSingleInstance = false
    ).build()

    // step3 - join a call, which type is `default` and id is `123`.
    call = client.call("livestream", callId)

    // join the cal
    val result = call?.join()
    result?.onError {
      Toast.makeText(context, "uh oh $it", Toast.LENGTH_SHORT).show()
    }
  }
}
