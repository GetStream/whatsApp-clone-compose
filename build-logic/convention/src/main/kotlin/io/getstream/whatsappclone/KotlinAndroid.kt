package io.getstream.whatsappclone

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
  commonExtension: CommonExtension<*, *, *, *>,
) {
  commonExtension.apply {
    compileSdk = 33

    defaultConfig {
      minSdk = 21
    }

    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_11
      targetCompatibility = JavaVersion.VERSION_11
      isCoreLibraryDesugaringEnabled = true
    }

    lint {
      abortOnError = false
    }

    kotlinOptions {
      // Treat all Kotlin warnings as errors (disabled by default)
      allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

      freeCompilerArgs = freeCompilerArgs + listOf(
        "-opt-in=kotlin.RequiresOptIn",
        // Enable experimental coroutines APIs, including Flow
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        // Enable experimental compose APIs
        "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",
      )

      // Set JVM target to 11
      jvmTarget = JavaVersion.VERSION_11.toString()
    }
  }

  val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

  dependencies {
    add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
  }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
  (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
