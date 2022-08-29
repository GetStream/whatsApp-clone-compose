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
  id("getstream.android.library.compose")
  id("getstream.spotless")
  id("com.google.devtools.ksp")
}

kotlin {
  sourceSets.configureEach {
    kotlin.srcDir("$buildDir/generated/ksp/$name/kotlin/")
  }
}

dependencies {
  implementation(project(":core-model"))

  implementation(libs.androidx.compose.runtime)

  implementation(libs.sealedx.core)
  ksp(libs.sealedx.processor)
}
