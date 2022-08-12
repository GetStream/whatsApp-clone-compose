plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("kotlin-kapt")
  id("com.google.devtools.ksp")
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

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

kotlin {
  sourceSets.configureEach {
    kotlin.srcDir("$buildDir/generated/ksp/$name/kotlin/")
  }
}

dependencies {
  api(project(":core-model"))
  api(project(":core-network"))
  api(project(":core-database"))

  api(Dependencies.streamClient)

  api(Dependencies.coroutines)

  api(Dependencies.hiltAndroid)
  kapt(Dependencies.hiltCompiler)

  implementation(Dependencies.sealedXCore)
  ksp(Dependencies.sealedXProcessor)
}
