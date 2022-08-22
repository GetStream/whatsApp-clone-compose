plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-parcelize")
  id("kotlin-kapt")
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

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  lint {
    abortOnError = false
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
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
