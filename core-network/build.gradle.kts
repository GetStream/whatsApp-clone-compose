plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
}

android {
  compileSdk = Configurations.compileSdk

  defaultConfig {
    minSdk = Configurations.minSdk
    targetSdk = Configurations.targetSdk
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation(project(":core-model"))

  api(Dependencies.okHttp)
  api(Dependencies.retrofit)
  api(Dependencies.retrofitResultAdapter)
  api(Dependencies.retrofitKotlinSerialization)
  api(Dependencies.kotlinSerializationJson)

  api(Dependencies.coroutines)

  implementation(Dependencies.hiltAndroid)
  kapt(Dependencies.hiltCompiler)
}
