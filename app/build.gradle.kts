/*
 * Copyright 2023 Stream.IO, Inc. All Rights Reserved.
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
  id("getstream.android.application")
  id("getstream.android.application.compose")
  id("getstream.android.hilt")
  id("getstream.spotless")
  id("kotlin-parcelize")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = "io.getstream.whatsappclone"
  compileSdk = Configurations.compileSdk

  defaultConfig {
    applicationId = "io.getstream.whatsappclone"
    minSdk = Configurations.minSdk
    targetSdk = Configurations.targetSdk
    versionCode = Configurations.versionCode
    versionName = Configurations.versionName
  }

  packagingOptions {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
  }

  buildTypes {
    create("benchmark") {
      isDebuggable = true
      signingConfig = getByName("debug").signingConfig
      matchingFallbacks += listOf("release")
    }
  }
}

dependencies {
  // core modules
  implementation(project(":core:designsystem"))
  implementation(project(":core:navigation"))
  implementation(project(":core:data"))

  // feature modules
  implementation(project(":features:camera"))
  implementation(project(":features:chats"))
  implementation(project(":features:status"))
  implementation(project(":features:calls"))

  // material
  implementation(libs.androidx.appcompat)

  // compose
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.constraintlayout)

  // jetpack
  implementation(libs.androidx.startup)
  implementation(libs.hilt.android)
  implementation(libs.androidx.hilt.navigation.compose)
  kapt(libs.hilt.compiler)

  // image loading
  implementation(libs.landscapist.glide)

  // pager
  implementation(libs.accompanist.pager)
  implementation(libs.accompanist.indicator)

  implementation(libs.stream.log)
}
