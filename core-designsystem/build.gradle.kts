plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "io.getstream.whatsappclone.designsystem"
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

  kotlinOptions {
    jvmTarget = "1.8"
  }

  packagingOptions {
    resources.excludes.add("META-INF/LICENSE.txt")
    resources.excludes.add("META-INF/NOTICE.txt")
    resources.excludes.add("LICENSE.txt")
    resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
  }
}

dependencies {
  api(Dependencies.composeUI)
  api(Dependencies.composeMaterial)
  api(Dependencies.composeMaterial3)
  api(Dependencies.composeMaterialIcon)
  api(Dependencies.composeFoundation)
  api(Dependencies.composeFoundationLayout)
  api(Dependencies.composeConstraintLayout)
  api(Dependencies.composeRuntime)
  api(Dependencies.composeTooling)
  api(Dependencies.landscapistGlide)
}
