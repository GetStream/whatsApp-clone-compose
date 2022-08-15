plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("com.google.devtools.ksp")
  id("dagger.hilt.android.plugin")
}

android {
  compileSdk = Configurations.compileSdk

  defaultConfig {
    // The schemas directory contains a schema file for each version of the Room database.
    // This is required to enable Room auto migrations.
    // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
    ksp {
      arg("room.schemaLocation", "$projectDir/schemas")
    }

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

  implementation(Dependencies.roomRuntime)
  implementation(Dependencies.roomKtx)
  ksp(Dependencies.roomCompiler)

  implementation(Dependencies.hiltAndroid)
  kapt(Dependencies.hiltCompiler)
}
