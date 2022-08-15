plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
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

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
  }

  lint {
    abortOnError = false
  }

  packagingOptions {
    resources.excludes.add("META-INF/LICENSE.txt")
    resources.excludes.add("META-INF/NOTICE.txt")
    resources.excludes.add("LICENSE.txt")
    resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
  }
}

dependencies {
  // core modules
  implementation(project(":core-designsystem"))
  implementation(project(":core-navigation"))
  implementation(project(":core-network"))
  implementation(project(":core-data"))

  // Stream chat Compose
  api(Dependencies.streamCompose)

  implementation(Dependencies.composeLifecycle)
  implementation(Dependencies.appStartUp)
  implementation(Dependencies.timber)

  implementation(Dependencies.hiltAndroid)
  implementation(Dependencies.hiltNavigation)
  kapt(Dependencies.hiltCompiler)
}
