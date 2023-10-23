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
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface StreamVideoEntryPoint {

  fun inject(streamVideoInitializer: StreamVideoInitializer)

  companion object {

    fun resolve(context: Context): StreamVideoEntryPoint {
      val appContext = context.applicationContext ?: throw IllegalStateException(
        "applicationContext was not found in NetworkEntryPoint"
      )
      return EntryPointAccessors.fromApplication(
        appContext,
        StreamVideoEntryPoint::class.java
      )
    }
  }
}
