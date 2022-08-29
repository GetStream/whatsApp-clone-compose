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
    resources.excludes.add("META-INF/LICENSE.txt")
    resources.excludes.add("META-INF/NOTICE.txt")
    resources.excludes.add("LICENSE.txt")
    resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
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
  implementation(project(":core-designsystem"))
  implementation(project(":core-navigation"))
  implementation(project(":core-data"))

  // feature modules
  implementation(project(":feature-camera"))
  implementation(project(":feature-chats"))
  implementation(project(":feature-status"))
  implementation(project(":feature-calls"))

  // material
  implementation(Dependencies.material)

  // compose
  implementation(Dependencies.composeActivity)
  implementation(Dependencies.composeAnimation)
  implementation(Dependencies.composeRuntime)
  implementation(Dependencies.composeTooling)
  implementation(Dependencies.composeConstraintLayout)

  // jetpack
  implementation(Dependencies.appStartUp)
  implementation(Dependencies.hiltAndroid)
  implementation(Dependencies.hiltNavigation)
  kapt(Dependencies.hiltCompiler)

  // image loading
  implementation(Dependencies.landscapistGlide)

  // pager
  implementation(Dependencies.accompanistPager)
  implementation(Dependencies.accompanistIndicator)

  implementation(Dependencies.timber)
}
