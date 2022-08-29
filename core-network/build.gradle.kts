/*
 * Copyright 2022 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
  id("getstream.android.library")
  id("getstream.android.hilt")
  id("getstream.spotless")
  id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
  implementation(project(":core-model"))

  api(libs.okhttp.logging)
  api(libs.retrofit.core)
  api(libs.retrofit.result.adapter)

  api(libs.retrofit.kotlin.serialization)
  api(libs.kotlinx.serialization.json)
}
