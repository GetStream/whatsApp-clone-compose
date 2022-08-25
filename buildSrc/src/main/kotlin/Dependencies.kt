object Versions {
  internal const val ANDROID_GRADLE_PLUGIN = "7.2.1"
  internal const val ANDROID_GRADLE_SPOTLESS = "6.7.0"
  internal const val KOTLIN = "1.7.10"
  internal const val KOTLIN_SERIALIZATION_JSON = "1.4.0"
  internal const val KSP = "1.7.10-1.0.6"

  internal const val COMPOSE = "1.2.1"
  const val COMPOSE_COMPILER = "1.3.0"
  internal const val COMPOSE_MATERIAL3 = "1.0.0-alpha13"
  internal const val COMPOSE_ACTIVITY = "1.4.0"
  internal const val COMPOSE_CONSTRAINT = "1.0.0"
  internal const val COMPOSE_NAVIGATION = "2.5.0"

  internal const val COROUTINES = "1.6.3"

  internal const val HILT = "2.42"
  internal const val HILT_NAVIGATION = "1.0.0"
  internal const val MATERIAL = "1.5.0"
  internal const val APP_STARTUP = "1.1.1"
  internal const val LIFECYCLE = "2.6.0-alpha01"
  internal const val ROOM = "2.4.2"
  internal const val SEALEDX = "1.0.0"

  internal const val LANDSCAPIST_GLIDE = "1.6.1"
  internal const val ACCOMPANIST = "0.25.0"

  internal const val STREAM_CHAT = "5.8.2"

  internal const val RETROFIT = "2.9.0"
  internal const val RETROFIT_RESULT_ADAPTER = "1.0.1"
  internal const val RETROFIT_KOTLIN_SERIALIZATION = "0.8.0"
  internal const val OKHTTP = "4.9.3"

  internal const val TIMBER = "5.0.0"

  internal const val ANDROIDX_TEST_VERSION = "1.4.0"
  internal const val BASE_PROFILE_VERSION = "1.2.0-rc01"
  internal const val MACRO_BENCHMARK_VERSION = "1.1.0"
  internal const val ANDROIDX_UI_AUTOMATOR_VERSION = "2.2.0"
}

object Dependencies {
  const val androidGradlePlugin =
    "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}"
  const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
  const val kotlinSerializationPlugin =
    "org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}"
  const val kotlinSerializationJson =
    "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLIN_SERIALIZATION_JSON}"
  const val ksp = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.KSP}"
  const val spotlessGradlePlugin =
    "com.diffplug.spotless:spotless-plugin-gradle:${Versions.ANDROID_GRADLE_SPOTLESS}"
  const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"

  const val material = "com.google.android.material:material:${Versions.MATERIAL}"
  const val composeUI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
  const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
  const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"
  const val composeFoundationLayout =
    "androidx.compose.foundation:foundation-layout:${Versions.COMPOSE}"
  const val composeActivity = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
  const val composeLifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.LIFECYCLE}"
  const val composeMaterial = "androidx.compose.material:material:${Versions.COMPOSE}"
  const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.COMPOSE_MATERIAL3}"
  const val composeMaterialIcon =
    "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"
  const val composeAnimation = "androidx.compose.animation:animation:${Versions.COMPOSE}"
  const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
  const val composeConstraintLayout =
    "androidx.constraintlayout:constraintlayout-compose:${Versions.COMPOSE_CONSTRAINT}"
  const val composeNavigation =
    "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"

  const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
  const val landscapistGlide = "com.github.skydoves:landscapist-glide:${Versions.LANDSCAPIST_GLIDE}"
  const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.ACCOMPANIST}"
  const val accompanistIndicator =
    "com.google.accompanist:accompanist-pager-indicators:${Versions.ACCOMPANIST}"
  const val streamCompose = "io.getstream:stream-chat-android-compose:${Versions.STREAM_CHAT}"
  const val streamClient = "io.getstream:stream-chat-android-client:${Versions.STREAM_CHAT}"

  const val appStartUp = "androidx.startup:startup-runtime:${Versions.APP_STARTUP}"
  const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.HILT}"
  const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.HILT}"
  const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION}"
  const val roomRuntime = "androidx.room:room-runtime:${Versions.ROOM}"
  const val roomKtx = "androidx.room:room-ktx:${Versions.ROOM}"
  const val roomCompiler = "androidx.room:room-compiler:${Versions.ROOM}"
  const val sealedXCore = "com.github.skydoves:sealedx-core:${Versions.SEALEDX}"
  const val sealedXProcessor = "com.github.skydoves:sealedx-processor:${Versions.SEALEDX}"

  const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
  const val retrofitResultAdapter =
    "com.github.skydoves:retrofit-adapters-result:${Versions.RETROFIT_RESULT_ADAPTER}"
  const val retrofitKotlinSerialization =
    "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.RETROFIT_KOTLIN_SERIALIZATION}"

  const val timber = "com.jakewharton.timber:timber:${Versions.TIMBER}"

  const val profileInstaller =
    "androidx.profileinstaller:profileinstaller:${Versions.BASE_PROFILE_VERSION}"
  const val macroBenchmark =
    "androidx.benchmark:benchmark-macro-junit4:${Versions.MACRO_BENCHMARK_VERSION}"
  const val uiAutomator =
    "androidx.test.uiautomator:uiautomator:${Versions.ANDROIDX_UI_AUTOMATOR_VERSION}"
  const val androidXTestRunner = "androidx.test:runner:${Versions.ANDROIDX_TEST_VERSION}"
}
